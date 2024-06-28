package ma.inetum.brique.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import ma.inetum.brique.model.principale.CostingDetails;
import ma.inetum.brique.model.principale.CostingHeader;
import ma.inetum.brique.model.principale.EcritureComptable;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParamCompteCosting;
import ma.inetum.brique.model.principale.Parametrage;
import ma.inetum.brique.model.principale.ParametrageSupplier;
import ma.inetum.brique.model.principale.PieceComptable;
import ma.inetum.brique.model.principale.Simulation;
import ma.inetum.brique.model.principale.SimulationCosting;
import ma.inetum.brique.repository.metier.CostingHeaderRepository;
import ma.inetum.brique.repository.principale.CostingHeaderPRepository;
import ma.inetum.brique.repository.principale.ErreurRepository;
import ma.inetum.brique.repository.principale.ParamCompteCostingRepository;
import ma.inetum.brique.repository.principale.ParametrageRepository;
import ma.inetum.brique.repository.principale.ParametrageSupplierRepository;
import ma.inetum.brique.repository.principale.PieceComptableRepository;
import ma.inetum.brique.repository.principale.SimulationCostingRepository;
import ma.inetum.brique.repository.principale.SimulationRepository;
import ma.inetum.brique.services.CostingService;
import ma.inetum.brique.utilities.Constantes;
import ma.inetum.brique.utilities.NumeroPieceService;
import ma.inetum.brique.utilities.PieceComptableTool;

@Service
public class CostingServiceImpl implements CostingService {
	private final static Logger logger = LoggerFactory.getLogger(CostingServiceImpl.class);

	@Autowired
	private CostingHeaderPRepository costingHeaderPRepository;

	@Autowired
	private ParametrageRepository parametrageRepository;

	@PersistenceContext(unitName = "Main")
	private EntityManager entityManager;

	@Autowired
	private PieceComptableRepository pieceComptableRepository;

	@Autowired
	private ErreurRepository erreurRepository;

	@Autowired
	private ParamCompteCostingRepository paraCompteCostingRepository;

	@Autowired
	private CostingHeaderRepository costingHeaderMRepository;

	@Autowired
	private SimulationCostingRepository simulationRepository;
	@Autowired
	private NumeroPieceService numeroPieceService;
	@Autowired
	private ParametrageSupplierRepository supplierRepository;

	@Autowired
	private SimulationRepository iSimulationRepository;

	private PieceComptableMapper mapper = PieceComptableMapper.getInstance();
	
	private final List<String> ignoredAnalysisCode 			= Arrays.asList("E");
	private final List<String> supplierRelatedAnalysisCode 	= Arrays.asList("T");

	@Override
	public <T> List<List<String>> generer(List<String> header, LocalDate date, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String format = "yyyy/MM/dd";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		String query = String.format("select %s from %s h, %s d ", String.join(", ", header), tableName,
				tableNameDetails);
		query += " where h.magic = d.magic and h.CREDATE  <= to_date('" + dtf.format(date) + "', '" + format
				+ "') and (FLAG_ENVOI = 0 or FLAG_ENVOI is null)";
		Query statement = entityManager.createNativeQuery(query);
		logger.info("Costing Query : " + query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
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
	public <T> List<List<String>> generer(List<String> header, Long simulationId, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String tableSimulation = SimulationCosting.class.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d, %s r ", String.join(", ", header), tableName,
				tableNameDetails, tableSimulation);
		query += " where h.magic = d.magic and r.HEADER_ID = h.ID and r.SIMULATION_ID = " + simulationId;
		Query statement = entityManager.createNativeQuery(query);
		logger.info("Costing Query : " + query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
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
	public <T> List<List<String>> generer(List<String> header, Long first, Long last, Class<?> c1, Class<?> c2) {
		String tableName = c1.getAnnotation(Table.class).name();
		String tableNameDetails = c2.getAnnotation(Table.class).name();
		String query = String.format("select %s from %s h, %s d ", String.join(", ", header), tableName,
				tableNameDetails);
		query += " where h.magic = d.magic and h.magic between " + first + " and " + last;
		logger.info("Costing Query : " + query);
		Query statement = entityManager.createNativeQuery(query);
		List<Object[]> results = statement.getResultList();
		List<List<String>> details = new ArrayList<>();
		for (Object[] result : results) {
			List<String> list = new LinkedList<>();
			for (Object value : result) {
				if (value != null) {
					list.add(value.toString());
				} else {
					list.add("");
				}
			}
			details.add(list);
		}
		return details;
	}

	public List<CostingHeader> findAllByDate(LocalDate date) {
		List<CostingHeader> headers = new ArrayList<CostingHeader>();

		return headers;
	}

	private List<Erreur> valider(List<CostingHeader> headers, Map<String, Map<String, String>> parametrages, List<Parametrage> refTvaList) {
		List<Erreur> errors = new ArrayList<>();
		Integer i = 0;
		if (headers.isEmpty()) {
			errors.add(new Erreur(0l, Long.valueOf(0), "La liste générer est vide "));
		}

		for (CostingHeader header : headers) {
			i = i + 1;

			if (header.getSupacc() == null) {
				errors.add(new Erreur(header.getMagic(), 0l, "Le code fournisseur n'est pas renseigné"));
			} else if (parametrages.get("supplier") == null
					|| parametrages.get("supplier").get(header.getSupacc().trim()) == null) {
				errors.add(new Erreur(header.getMagic(), 0l,
						"Le code fournisseur (" + header.getSupacc() + ") n'est pas paramétré "));
			}
			if (header.getVatcode() == null) {
				errors.add(new Erreur(header.getMagic(), 0l, "Code TVA n'est pas renseigné"));
			} else if (refTvaList == null) {
				errors.add(new Erreur(header.getMagic(), 0l,
						"Code TVA (" + header.getVatcode() + ") n'est pas paramétré "));
			} else {
				boolean found = false;
				if (Constantes.SUPPLIER_DOUANE_CODE.equalsIgnoreCase(header.getSupacc())) {
					Optional  tva = refTvaList.stream().filter(e-> Constantes.SUPPLIER_DOUANE_CODE.equalsIgnoreCase(e.getAddtionalField()) && e.getCodeMedtier().equals(header.getVatcode())).findAny();
					if (!tva.isEmpty()) {
						found = true;
					}
				} else {
					Optional  tva = refTvaList.stream().filter(e-> e.getAddtionalField() == null  && e.getCodeMedtier().equals(header.getVatcode())).findAny();
					if (!tva.isEmpty()) {
						found = true;
					}					
				}
				if (!found) {
					errors.add(new Erreur(header.getMagic(), 0l,
							"Code TVA (" + header.getVatcode() + ") non paramétré pour ce fournisseur (" +header.getSupacc() +")"));
				}
			}
			if (header.getDetails() == null || header.getDetails().size() == 0) {
				errors.add(new Erreur(header.getMagic(), 0l, "La liste des détails est vide"));
			} else {

				int j = 0;
				for (CostingDetails detail : header.getDetails()) {
					j++;
					/*
					if (detail.getVatcode() == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(i),
								"Code TVA n'est pas renseigné pour la ligne du détail numéro " + j));
					} else if (parametrages.get("tva") == null
							|| parametrages.get("tva").get(detail.getVatcode()) == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j),
								"Code TVA (" + detail.getVatcode() + ") n'est pas paramétré "));
					}
					*/
					if (detail.getFran() == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j),
								"Marque n'est pas renseigné pour la ligne du détail numéro " + j));
					} else if (parametrages.get("marque") == null
							|| parametrages.get("marque").get(detail.getFran()) == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j),
								"Marque (" + detail.getFran() + ") n'existe pas "));
					}
					
					if (detail.getAnalysis() == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Code analyse absent !! "));
					}
					if (detail.getStatus() == null) {
						errors.add(new Erreur(header.getMagic(), Long.valueOf(j), "Code status absent !! "));
					}
				}
			}
		}
		return errors;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Boolean, Object> simuler(Simulation simulation, LocalDate dateArret) throws CloneNotSupportedException {
		Map<Boolean, Object> resultat = new HashMap<>();
		List<CostingHeader> headers = costingHeaderPRepository.findAllBy(dateArret);
		Map<String, Map<String, String>> parametrages = new HashMap<>();
		List<ParametrageSupplier> suppliers = supplierRepository.findAll();
		if (suppliers != null && !suppliers.isEmpty()) {
			Map<String, String> suppliersMap = new HashMap<>();
			for (ParametrageSupplier supplier : suppliers) {
				suppliersMap.put(supplier.getCodeMedtier(), supplier.getCodeFinance());
			}
			parametrages.put("supplier", suppliersMap);
		}

		parametrages.put("marque", parametrageRepository.findByCategorie("marque").stream().collect(
				Collectors.toMap(Parametrage::getCodeMedtier, Parametrage::getCodeFinance, (elem1, elem2) -> elem2)));
		List<Parametrage> refTvaList = parametrageRepository.findByCategorie("tva");
		List<ParamCompteCosting> schemaComptables = paraCompteCostingRepository.findByFlux(Constantes.FLUX_COSTING);
		List<PieceComptable> piecesCosting = new ArrayList<>();
		List<PieceComptable> piecesStock = new ArrayList<>();
		List<Erreur> erreurs = valider(headers, parametrages, refTvaList);
		simulation = iSimulationRepository.getOne(simulation.getId());
		if (!erreurs.isEmpty()) {
			resultat.put(false, getErrorFormated(erreurs, simulation));
		} else {
			List<PieceComptableView> pieces = new ArrayList<>();
			List<SimulationCosting> costingList = new ArrayList<>();
			Long lastnumerIMP = numeroPieceService.getNumeroPiece(Constantes.CODE_JRNL_IMPORT_VHL);
			
			for (CostingHeader header : headers) {

				PieceComptable piece = new PieceComptable();
				costingList.add(new SimulationCosting(header, simulation));
				piece.setCodeJournale(Constantes.CODE_JRNL_IMPORT_VHL);
				piece.setDate(header.getInvdate());
				piece.setNumeroPiece(lastnumerIMP++);
				piece.setEnteteDoc(header.getImfileno());
				piece.setFlux(simulation.getFlux());
				piece.setSimulation(simulation);

				Map<String, EcritureComptable> mapEcrituresTVA = new HashMap<>();
				Map<String, EcritureComptable> mapEcrituresClass4 = new HashMap<>();
				
				long cptrDetail = 0;
				
				String docType = header.getDocType()!=null?header.getDocType().trim():"";
				if (!docType.equalsIgnoreCase(Constantes.COSTING_INVOICE_DOC_TYPE) && !docType.equalsIgnoreCase(Constantes.COSTING_CREDIT_NOTE_DOC_TYPE)) {
					logger.info("Facture frais d'approche ignorée. DocType = '" + docType + "'");
					continue;
				}
				for (CostingDetails detail : header.getDetails()) {
					cptrDetail++;
					
					//Liste des codes d'analyse à ignorer
					if (ignoredAnalysisCode.contains(detail.getAnalysis().trim().toUpperCase())) {
						logger.info("Code analyse ignoré : " + detail.getAnalysis());
						continue;
					}

					ParamCompteCosting paramCompteCosting = null;

					if (supplierRelatedAnalysisCode.contains(detail.getAnalysis().trim().toUpperCase())) {
						var schCpt = schemaComptables.stream()
								.filter(t -> t.getCodeAnalyse().equals(detail.getAnalysis().trim())
										&& t.getCodeFournisseur().equals(header.getSupacc().trim()))
								.findAny();
						if ((schCpt == null) || (schCpt.isEmpty())) {
							erreurs.add(new Erreur(header.getMagic(), cptrDetail,
									"Aucun schéma comptable n'est paramétré pour ce flux (Frais d'approche) avec ce fournisseur ("+header.getSupacc().trim()+") et ce code d'analyse ("+detail.getAnalysis().trim()+")"));
						} else {
							paramCompteCosting = schCpt.get();
						}
					} else {
						var schCpt = schemaComptables.stream()
								.filter(t -> t.getCodeAnalyse().equalsIgnoreCase(detail.getAnalysis())).findAny();
						if ((schCpt == null) || (schCpt.isEmpty())) {
							erreurs.add(new Erreur(header.getMagic(), cptrDetail,
									"Aucun schéma comptable n'est paramétré pour ce flux (Frais d'approche) avec ce fournisseur ("+header.getSupacc().trim()+") et ce code d'analyse ("+detail.getAnalysis().trim()+")"));
						} else {
							paramCompteCosting = schCpt.get();
						}
					}

					if (paramCompteCosting != null) {
						
						if ((paramCompteCosting.getCompteTVA()!=null) && (header.getVatvalue()!=null) && (header.getVatvalue() > 0d)) {
							if (mapEcrituresTVA.get(paramCompteCosting.getCompteTVA()) == null) {
								Optional<Parametrage> tva = null;
								if (Constantes.SUPPLIER_DOUANE_CODE.equalsIgnoreCase(header.getSupacc().trim())) {
									tva = refTvaList.stream().filter(e-> Constantes.SUPPLIER_DOUANE_CODE.equalsIgnoreCase(e.getAddtionalField()) && e.getCodeMedtier().equals(header.getVatcode())).findAny();									
								} else {
									tva = refTvaList.stream().filter(e-> e.getAddtionalField() == null  && e.getCodeMedtier().equals(header.getVatcode())).findAny();									
								}
								Parametrage tvaRef = tva.get();
								EcritureComptable ecriture = genererEcritureTVA(paramCompteCosting, tvaRef.getCodeFinance(), header.getVatvalue());
								ecriture = addReferencesExternes(ecriture, header);
								//surcharges Réf 3 & 5
								ecriture.setRef3(detail.getChassis()!=null?detail.getChassis().trim():"");
								ecriture.setRef5(detail.getVehicle()!=null?String.valueOf(detail.getVehicle()):"");
								ecriture.setPiece(piece);
								piece.getEcritures().add(ecriture);
								mapEcrituresTVA.put(paramCompteCosting.getCompteTVA(), ecriture);
							}
							
						}
						if ((paramCompteCosting.getCompteDebiteur() != null) && (detail.getInvalue()!=null) && (detail.getInvalue() > 0d)) {
							EcritureComptable ecriture = genererEcritureClasse6(paramCompteCosting, detail.getInvalue(), parametrages.get("marque").get(detail.getFran().trim()));
							ecriture = addReferencesExternes(ecriture, header);
							ecriture.setPiece(piece);
							//surcharges Réf 3 & 5
							ecriture.setRef3(detail.getChassis()!=null?detail.getChassis().trim():"");
							ecriture.setRef5(detail.getVehicle()!=null?String.valueOf(detail.getVehicle()):"");
							piece.getEcritures().add(ecriture);
						}
						if ((paramCompteCosting.getCompteCrediteur() !=null) && (detail.getInvalue()!=null) && (detail.getInvalue() > 0d)) {
							BigDecimal mnt = BigDecimal.valueOf(detail.getInvalue());
							EcritureComptable ecriture = null;
							
							if (mapEcrituresClass4.get(paramCompteCosting.getCompteCrediteur()) != null) {
								ecriture = mapEcrituresClass4.get(paramCompteCosting.getCompteCrediteur());
								mnt = mnt.add(BigDecimal.valueOf(ecriture.getMontantMAD()));
								ecriture.setMontantMAD(mnt.doubleValue());
							} else {
								ecriture = genererEcritureClasse4(paramCompteCosting, mnt.doubleValue(), header, detail, parametrages);
								ecriture = addReferencesExternes(ecriture, header);
								//surcharges Réf 3 & 5
								ecriture.setRef3(detail.getChassis()!=null?detail.getChassis().trim():"");
								ecriture.setRef5(detail.getVehicle()!=null?String.valueOf(detail.getVehicle()):"");
								
							}
							mapEcrituresClass4.put(paramCompteCosting.getCompteCrediteur(), ecriture);
						}
					}					
				}
				Double vatValue = 0d;
				if (!mapEcrituresTVA.isEmpty()) {
					vatValue = mapEcrituresTVA.get(mapEcrituresTVA.keySet().iterator().next()).getMontantMAD();
				}
				
				if (!mapEcrituresClass4.isEmpty()) {
					for (String compteDebiteur : mapEcrituresClass4.keySet()) {
						EcritureComptable ecriture = mapEcrituresClass4.get(compteDebiteur);
						BigDecimal mntCredit = BigDecimal.valueOf(vatValue).add(BigDecimal.valueOf(ecriture.getMontantMAD()));
						ecriture.setMontantMAD(mntCredit.doubleValue());
						ecriture.setPiece(piece);
						piece.getEcritures().add(ecriture);
					}
				}					
				
				PieceComptableTool pcTool = new PieceComptableTool(piece, header.getMagic());
				if (!pcTool.isPieceValide()) {
					erreurs.addAll(pcTool.getErreurs());
					continue;
				}
				
				piecesCosting.add(piece);
				
				PieceComptable pieceStock = genererPieceStock(header, simulation, parametrages);
				if (pieceStock.getEcritures().size() > 0) {
					piecesStock.add(pieceStock);
				}
				
				//Prise en charge du DOCTYPE "C"
				if (Constantes.COSTING_CREDIT_NOTE_DOC_TYPE.equals(docType)) {
					for (EcritureComptable ecriture : piece.getEcritures()) {
						if (Constantes.SENS_CREDIT == ecriture.getSens()) {
							ecriture.setSens(Constantes.SENS_DEBIT);
						} else {
							ecriture.setSens(Constantes.SENS_CREDIT);
						}
					}
				}
				
			}
			if (!erreurs.isEmpty()) {
				resultat.put(false, getErrorFormated(erreurs, simulation));
			} else {
				piecesCosting.addAll(piecesStock);
				for (var p : piecesCosting) {
					pieceComptableRepository.save(p);
					pieces.add(mapper.entityTobean(p));

				}
				simulationRepository.saveAll(costingList);
				resultat.put(true, pieces);
			}
		}
		return resultat;
	}

	public List<ma.inetum.brique.model.metier.CostingHeader> findAllForBatch(Long firstElementToLoad) {
		return costingHeaderMRepository.findAllForBatch(firstElementToLoad);
	}

	private PieceComptable genererPieceStock(CostingHeader header, Simulation simulation, Map<String, Map<String, String>> parametrages) {
		
		PieceComptable pieceStock = new PieceComptable();
		pieceStock.setCodeJournale(Constantes.CODE_JRNL_STOCK_VHL);
		pieceStock.setDate(header.getInvdate());
		Long lastnumerSTOCK = numeroPieceService.getNumeroPiece(Constantes.CODE_JRNL_STOCK_VHL);
		Map<String, Map<Long, List<CostingDetails>>> map = header.getDetails().stream()
				.filter(e -> Arrays.asList("S", "T", "I", "D").contains(e.getStatus().trim().toUpperCase()))
				.collect(Collectors.groupingBy(e -> e.getLocn(),
						Collectors.groupingBy(CostingDetails::getVehicle)));

		pieceStock.setNumeroPiece(lastnumerSTOCK++);
		pieceStock.setEnteteDoc(header.getImfileno());
		pieceStock.setFlux(simulation.getFlux());
		pieceStock.setSimulation(simulation);
		for (var location : map.keySet()) {
			for (var vehicule : map.get(location).keySet()) {
				double totale = ((int) ((map.get(location).get(vehicule).stream()
						.mapToDouble(e -> e.getInvalue()).sum()) * 100)) / 100.0;
				if (totale == 0) {
					continue;
				}
				EcritureComptable ecriture = new EcritureComptable();
				ecriture.setRef1(header.getInvno() != null ? header.getInvno().toString() : "");
				ecriture.setRef2(header.getImcommno());
				String chassis = ((map.get(location).get(vehicule) !=null) && (!map.get(location).get(vehicule).isEmpty()))?map.get(location).get(vehicule).get(0).getChassis():"";
				ecriture.setRef3(chassis);
				ecriture.setRef4(header.getShipno());
				ecriture.setRef5(vehicule.toString());
				String franDesc = ((map.get(location).get(vehicule) !=null) && (!map.get(location).get(vehicule).isEmpty()))?map.get(location).get(vehicule).get(0).getFrandesc():"";
				ecriture.setRef6(franDesc);
				ecriture.setPiece(pieceStock);
				ecriture.setNcpt("311100");
				ecriture.setMontantMAD(totale);
				ecriture.setCostCenter(location + ".AVN."
						+ parametrages.get("marque").get(map.get(location).get(vehicule).get(0).getFran()));
				ecriture.setSens('D');
				ecriture.setAccountDescription("Coût additionnel sur stock véhicule ("
						+ String.join(", ", map.get(location).get(vehicule).stream().map(e -> e.getAnaldesc())
								.collect(Collectors.toList()))
						+ ")");
				pieceStock.getEcritures().add(ecriture);
				ecriture = new EcritureComptable();
				ecriture.setRef1(header.getInvno() != null ? header.getInvno().toString() : "");
				ecriture.setRef2(header.getImcommno());
				ecriture.setRef3(String.join(",", header.getDetails().stream().map(e -> e.getChassis().trim())
						.distinct().collect(Collectors.toList())));
				ecriture.setRef4(header.getShipno());
				ecriture.setRef5(String.join(",", header.getDetails().stream()
						.map(e -> e.getVehicle().toString()).distinct().collect(Collectors.toList())));
				ecriture.setRef6(String.join(",", header.getDetails().stream()
						.map(e -> e.getFrandesc().toString()).distinct().collect(Collectors.toList())));
				ecriture.setPiece(pieceStock);
				ecriture.setSens('C');
				ecriture.setAccountDescription("Variation Stock Vles Neufs");
				ecriture.setNcpt("611400");
				ecriture.setMontantMAD(totale);
				ecriture.setCostCenter(location + ".AVN."
						+ parametrages.get("marque").get(map.get(location).get(vehicule).get(0).getFran()));
				pieceStock.getEcritures().add(ecriture);
			}
		}
		return pieceStock;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean saveAll(List<CostingHeader> list) {
		List<Erreur> erreurs = new ArrayList<>();
		for (var element : list) {
			if (element.getDetails() == null || element.getDetails().size() == 0) {
				Erreur erreur = new Erreur(element.getMagic(), null, "Element n'a pas de détails");
				erreur.setChargement(element.getChargement());
				erreurs.add(new Erreur(element.getMagic(), null, "Element n'a pas de détails"));
			}
		}
		if (erreurs.isEmpty()) {
			costingHeaderPRepository.saveAll(list);
			return true;
		} else {
			erreurRepository.saveAll(erreurs);
			return false;
		}

	}

	@Transactional(value = "chainedTransactionManager")
	@Override
	public Boolean generer(Simulation simulation) {
		LocalDateTime now = LocalDateTime.now();
		List<CostingHeader> headers = simulationRepository.findBySimulation(simulation);
		for (var header : headers) {
			header.setDateEnvoi(now);
			header.setSent(true);
		}
		costingHeaderPRepository.saveAll(headers);
		return true;
	}

	private Map<String, List<String>> getErrorFormated(List<Erreur> erreurs, Simulation simulation) {
		Map<String, List<String>> map = new HashMap<>();
		if (!erreurs.isEmpty()) {
			simulation.setErreurs(erreurs);
			for (Erreur e : erreurs) {
				if (map.get(e.getMagic().toString()) == null) {
					map.put(e.getMagic().toString(), new ArrayList<>());
				}
				map.get(e.getMagic().toString()).add(e.getDescription());
				e.setSimulation(simulation);
			}
		}
		return map;
	}
	
	private EcritureComptable genererEcritureClasse6 (ParamCompteCosting paramCompteCosting, Double amount, String franCode ) {
		EcritureComptable ecriture = new EcritureComptable();
		ecriture.setNcpt(paramCompteCosting.getCompteDebiteur());
		ecriture.setMontantMAD(amount);
		ecriture.setAccountDescription(paramCompteCosting.getCompteDebiteurDescription());
		ecriture.setSens(Constantes.SENS_DEBIT);
		if (paramCompteCosting.getCostCenter() != null && !paramCompteCosting.getCostCenter().isBlank()) {
			ecriture.setCostCenter(paramCompteCosting.getCostCenter() + "." + franCode);
		}
		
		return ecriture;
	}
	
	private EcritureComptable genererEcritureClasse4 (ParamCompteCosting paramCompteCosting, Double mntDebit, CostingHeader header, CostingDetails detail, Map<String, Map<String, String>> parametrages) {
		EcritureComptable ecriture = new EcritureComptable();

		String account = paramCompteCosting.getCompteCrediteur() + "." + parametrages.get("supplier").get(header.getSupacc().trim());
		ecriture.setNcpt(account);
		ecriture.setMontantMAD(mntDebit);
		ecriture.setAccountDescription(
				paramCompteCosting.getCompteCrediteurDescription() + " (" + detail.getAnaldesc() + ") ");
		ecriture.setSens(Constantes.SENS_CREDIT);

		return ecriture;
	}
	
	private EcritureComptable addReferencesExternes(EcritureComptable ecriture, CostingHeader header) {
		if ((ecriture != null) && (header != null)) {
			ecriture.setRef1(header.getInvoiceSupplierReference() != null ? header.getInvoiceSupplierReference().toString() : "");
			ecriture.setRef2(header.getImcommno() != null ? header.getImcommno().trim() : "");
			ecriture.setRef3(String.join(",", header.getDetails().stream().map(e -> e.getChassis().trim())
					.distinct().collect(Collectors.toList())));
			ecriture.setRef4(header.getInvoiceSupplierReferenceCompl()!=null?header.getInvoiceSupplierReferenceCompl().trim():"");
			ecriture.setRef5(String.join(",", header.getDetails().stream()
					.map(e -> e.getVehicle().toString()).distinct().collect(Collectors.toList())));
			ecriture.setRef6(header.getInvno() != null ? header.getInvno().toString() : "");			
		}
		return ecriture;
	}
	
	private EcritureComptable genererEcritureTVA(ParamCompteCosting paramCompteCosting, String vatCode, Double vatValue) {
		EcritureComptable ecriture = new EcritureComptable();
		ecriture.setNcpt(
				paramCompteCosting.getCompteTVA() + "." + vatCode);
		ecriture.setMontantMAD(vatValue);
		ecriture.setAccountDescription(paramCompteCosting.getTvaAccountDescription());
		ecriture.setSens(Constantes.SENS_DEBIT);
		return ecriture;
	}
}
