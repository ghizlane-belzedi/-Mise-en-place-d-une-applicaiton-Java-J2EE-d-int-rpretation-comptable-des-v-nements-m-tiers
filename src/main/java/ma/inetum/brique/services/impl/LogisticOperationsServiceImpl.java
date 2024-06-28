package ma.inetum.brique.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.inetum.brique.bean.PieceComptableView;
import ma.inetum.brique.mapper.PieceComptableMapper;
import ma.inetum.brique.model.metier.LogisticOperations;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.LogisticOperationsP;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationLogisticOperations;
import ma.inetum.brique.repository.metier.LogisticOperationsRepository;
import ma.inetum.brique.repository.principale.LogisticOperationsPRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationLogisticOperationsRepository;
import ma.inetum.brique.services.LogisticOperationsService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;

@Service
public class LogisticOperationsServiceImpl implements LogisticOperationsService {

	private final static Logger logger = LoggerFactory.getLogger(LogisticOperationsServiceImpl.class);
	
	@Autowired
    LogisticOperationsPRepository logisticOperationsPRepository;

    @Autowired
    LogisticOperationsRepository logisticOperationsRepository;
    @Autowired
    SimulationLogisticOperationsRepository simulationRepository;
    @Autowired
    private ParametrageRepository parametrageRepository;
    @Autowired
    PieceComptableRepository pieceComptableRepository;
    @Autowired
    NumeroPieceService numeroPiece;
    @PersistenceContext(unitName = "Main")
    EntityManager entityManager;
    @Autowired
    private NumeroPieceService numeroPieceService;
    
    private PieceComptableMapper mapper = PieceComptableMapper.getInstance();


    @Override
    public void saveAll(List<LogisticOperationsP> logisticOperationsList){
        logisticOperationsPRepository.saveAll(logisticOperationsList);
    }

    @Override
    public List<LogisticOperations> findAllForBatch(Long firstElementToLoad){
        return logisticOperationsRepository.findAllForBatch(firstElementToLoad);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException{

        Map<Boolean, Object> resultat = new HashMap<>();
        List<LogisticOperationsP> list = logisticOperationsPRepository.findAllByDate(dateArret.atStartOfDay().plusDays(1l).toLocalDate());
        Map<String, Map<String, String>> parametrages = new HashMap<>();
        parametrages.put("marque", parametrageRepository.findByCategorie("marque").stream()
                .collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 )));
        parametrages.put("site", parametrageRepository.findByCategorie("site").stream()
                .collect(Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2 )));

        List<Erreur> erreurs = valider(list, parametrages);
        if (!erreurs.isEmpty()){
            simulation.setErreurs(erreurs);
            Map<String, List<String>> map = new HashMap<>();
            for (Erreur e : erreurs) {
                if (map.get(e.getMagic().toString()) == null) {
                    map.put(e.getMagic().toString(), new ArrayList<>());
                }
                map.get(e.getMagic().toString()).add(e.getDescription());
                e.setSimulation(simulation);
            }
            resultat.put(false, map);
        }
        else{
            List<PieceComptableView> pieces = new ArrayList<>();
            List<SimulationLogisticOperations> simulationLogisticOperationsList = new ArrayList<>();
            Long lastnumerS = numeroPieceService.getNumeroPiece(Constantes.CODE_JRNL_STOCK_VHL);
            for(LogisticOperationsP element : list){
                PieceComptable piece = new PieceComptable();
                simulationLogisticOperationsList.add(new SimulationLogisticOperations(element, simulation));
                piece.setCodeJournale(Constantes.CODE_JRNL_STOCK_VHL);
                piece.setDate(element.getCredate());
                piece.setNumeroPiece(lastnumerS++);
                piece.setEnteteDoc("Stock véhicules neufs");
                piece.setFlux(simulation.getFlux());
                piece.setSimulation(simulation);
                String site = parametrages.get("site").get(element.getLocn().trim());
				String marque = parametrages.get("marque").get(element.getFran().trim());
                if(Constantes.OPERTYPE_S.equals(element.getOpertype())){
                    EcritureComptable ecritureD = new EcritureComptable();
                    ecritureD.setSens('D');
                    ecritureD.setAccountDescription("Stock Véhicules Neufs");
                    ecritureD.setNcpt("311100");
                    ecritureD.setMontantMAD(((int)(element.getCost() * 100)) / 100.0);
                    ecritureD.setRef2(element.getComm());
                    ecritureD.setRef3(element.getChassis());
                    if(element.getVehicle() != null){
                        ecritureD.setRef5(element.getVehicle().toString());
                    }
                    ecritureD.setRef6(element.getFrandesc());
                    ecritureD.setCostCenter(String.join(".", site, "AVN", marque));
                    ecritureD.setPiece(piece);

                    EcritureComptable ecritureC = (EcritureComptable) ecritureD.clone();
                    ecritureC.setSens('C');
                    ecritureC.setAccountDescription("Variation Stocks Vles Neufs");
                    ecritureC.setNcpt("611400");
                    ecritureC.setMontantMAD(((int)(element.getCost() * 100)) / 100.0);
                    ecritureC.setCostCenter(String.join(".", site, "AVN", marque));

                    List<EcritureComptable> ecritureComptableList = new ArrayList<>();
                    ecritureComptableList.add(ecritureC);
                    ecritureComptableList.add(ecritureD);
                    piece.setEcritures(ecritureComptableList);
                }
                /*
                else if(Constantes.OPERTYPE_T.equals(element.getOpertype())){
                    EcritureComptable ecritureC = new EcritureComptable();
                    ecritureC.setSens('C');
                    ecritureC.setAccountDescription("Stock Véhicules Neufs");
                    ecritureC.setNcpt("311100");
                    ecritureC.setMontantMAD(((int)(element.getCost() * 100)) / 100.0);
                    ecritureC.setCostCenter(String.join(".", site, "AVN", marque));
                    ecritureC.setRef2(element.getComm());
                    ecritureC.setRef3(element.getChassis());
                    if(element.getVehicle() != null){
                        ecritureC.setRef5(element.getVehicle().toString());
                    }
                    ecritureC.setRef6(element.getFrandesc());
                    ecritureC.setPiece(piece);

                    EcritureComptable ecritureD = (EcritureComptable) ecritureC.clone();
                    ecritureD.setSens('D');
                    ecritureD.setAccountDescription("Variation Stocks Vles Neufs");
                    ecritureD.setNcpt("611400");

                    EcritureComptable ecritureD1 = new EcritureComptable();
                    ecritureD1.setSens('D');
                    ecritureD1.setAccountDescription("Stock Véhicules Neufs");
                    ecritureD1.setNcpt("311100");
                    ecritureD1.setMontantMAD(((int)(element.getCost() * 100)) / 100.0);
                    ecritureD1.setCostCenter(String.join(".", parametrages.get("site").get(element.getLocnnew()), "AVN", marque));
                    ecritureD1.setRef2(element.getComm());
                    ecritureD1.setRef3(element.getChassis());
                    if(element.getVehicle() != null){
                        ecritureD1.setRef5(element.getVehicle().toString());
                    }
                    ecritureD1.setRef6(element.getFrandesc());
                    ecritureD1.setPiece(piece);

                    EcritureComptable ecritureC1 = (EcritureComptable) ecritureD1.clone();
                    ecritureC1.setSens('C');
                    ecritureC1.setAccountDescription("Variation Stocks Vles Neufs");
                    ecritureC1.setNcpt("611400");


                    List<EcritureComptable> ecritureComptableList = new ArrayList<>();
                    ecritureComptableList.add(ecritureC);
                    ecritureComptableList.add(ecritureD);
                    ecritureComptableList.add(ecritureD1);
                    ecritureComptableList.add(ecritureC1);
                    piece.setEcritures(ecritureComptableList);
                }
                */
                pieceComptableRepository.save(piece);
                pieces.add(mapper.entityTobean(piece));
            }
            simulationRepository.saveAll(simulationLogisticOperationsList);
            resultat.put(true, pieces);
        }
        return resultat;
    }
    @Override
    public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c){
        String tableName = c.getAnnotation(Table.class).name();
        String query = String.format("select %s from %s v ", String.join(", ", header), tableName);
        String format = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        query += " where v.CREDATE <= TO_DATE('" + dtf.format(date.plusDays(1)) + "', '"+ format +"') and (sent = 0 or sent is null) and (opertype in('S', 'T'))";
        Query statement = entityManager.createNativeQuery(query);
        List<Object[]> results = statement.getResultList();
        List<List<String>> details = new ArrayList<>();
        for (Object[] result : results) {
            List<String> list = new LinkedList<>();
            for (Object value : result) {
                if(value != null ) {
                    list.add(value.toString());
                } else {
                    list.add("");
                }
            }
            details.add(list);
        }
        return details;
    }
    @Override
    public <T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c){
        String tableName = c.getAnnotation(Table.class).name();
        String tableSimulation = SimulationLogisticOperations.class.getAnnotation(Table.class).name();
        String query = String.format("select %s from %s v, %s r ", String.join(", ", header), tableName, tableSimulation);
        query += " where r.LOGISTIC_OPERATIONS_ID = v.ID and r.SIMULATION_ID = " + simulationId ;
        logger.info("Logistic query" +query);
        Query statement = entityManager.createNativeQuery(query);
        List<Object[]> results = statement.getResultList();
        List<List<String>> details = new ArrayList<>();
        for (Object[] result : results) {
            List<String> list = new LinkedList<>();
            for (Object value : result) {
                if(value != null ) {
                    list.add(value.toString());
                } else {
                    list.add("");
                }
            }
            details.add(list);
        }
        return details;
    }
    private List<Erreur> valider(List<LogisticOperationsP> logisticOperationsPList, Map<String, Map<String, String>> parametrages){
        List<Erreur> errors = new ArrayList<>();
        Integer numeroLigne = 0;
        if (logisticOperationsPList.isEmpty()) {
            errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
        }
        for(LogisticOperationsP logisticOperationsP : logisticOperationsPList){
            if (logisticOperationsP.getLocn() == null) {
                errors.add(new Erreur(logisticOperationsP.getMagic(), Long.valueOf(numeroLigne), "Ancien site n'est pas renseigné"));
            }
            else if(parametrages.get("site") == null
                    || parametrages.get("site").get(logisticOperationsP.getLocn()) == null){
                errors.add(new Erreur(logisticOperationsP.getMagic(), Long.valueOf(numeroLigne), "Ancien site (" + logisticOperationsP.getLocn() + ") n'existe pas "));
            }

            if (logisticOperationsP.getLocnnew() == null) {
                errors.add(new Erreur(logisticOperationsP.getMagic(), Long.valueOf(numeroLigne), "Nouveau site n'est pas renseigné"));
            }
            else if(parametrages.get("site") == null
                    || parametrages.get("site").get(logisticOperationsP.getLocnnew()) == null){
                errors.add(new Erreur(logisticOperationsP.getMagic(), Long.valueOf(numeroLigne), "Nouveau site (" + logisticOperationsP.getLocnnew() + ") n'existe pas "));
            }

            if(logisticOperationsP.getFran() == null){
                errors.add(new Erreur(logisticOperationsP.getMagic(), Long.valueOf(numeroLigne), "Marque n'est pas renseigné"));
            }
            else if (parametrages.get("marque") == null
                    || parametrages.get("marque").get(logisticOperationsP.getFran()) == null) {
                errors.add(new Erreur(logisticOperationsP.getMagic(), Long.valueOf(numeroLigne), "Marque (" + logisticOperationsP.getFran() +") n'existe pas "));
            }

        }
        return errors;
    }
    @Transactional(value = "chainedTransactionManager")
    @Override
    public Boolean generer(Simulation simulation){
        LocalDateTime now = LocalDateTime.now();
        List<LogisticOperationsP> logisticOperationsPList = simulationRepository.findBySimulation(simulation);
        for (var logisticOperations : logisticOperationsPList) {
            logisticOperations.setDateEnvoi(now);
            logisticOperations.setSent(true);
        }
        logisticOperationsPRepository.saveAll(logisticOperationsPList);
        return true;
    }

    @Override
    public <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c){
        String tableName = c.getAnnotation(Table.class).name();
        String query = String.format("select %s from %s v ", String.join(", ", header), tableName);
        query += " where v.magic between " + first + " and " + (last!=null?last:0l);
        logger.info("Logistic operation Query : " + query);
        Query statement = entityManager.createNativeQuery(query);
        List<Object[]> results = statement.getResultList();
        List<List<String>> details = new ArrayList<>();
        for (Object[] result : results) {
            List<String> list = new LinkedList<>();
            for (Object value : result) {
                if(value != null ) {
                    list.add(value.toString());
                } else {
                    list.add("");
                }
            }
            details.add(list);
        }
        return details;
    }

}
