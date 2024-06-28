package ma.inetum.brique.batch;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.LogisticOperationsMapper;
import ma.inetum.brique.model.metier.LogisticOperations;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.LogisticOperationsService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.utilities.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LogisticOperationsBatchLoader {

    @Autowired
    ParametrageFluxService paramFluxService;
    @Autowired
    ChargementService chargementService;
    @Autowired
    LogisticOperationsService logisticOperationsService;


    private final static Logger logger = LoggerFactory.getLogger(LogisticOperationsBatchLoader.class);
    public void loadLogisticOperations() throws ExceptionFonctionnelle {
        Chargement newChargement = null;
        try {
            ParametrageFLux flux = paramFluxService.findFluxByCode(Constantes.FLUX_Logistic_Operations);
            if (flux != null) {
                Chargement chargement = chargementService.findLastChargementIdByFluxAndChargementState(flux, ChargementState.TERMINER_SUCCESS);
                Long firstElementToLoad = chargement != null ? chargement.getLast() + 1 : 1l;
                newChargement = chargementService.initChargement(flux, firstElementToLoad);
                List<LogisticOperations> list =  logisticOperationsService.findAllForBatch(firstElementToLoad);
                if ((list == null) || (list.size() == 0)) {
                    logger.info("AUCUN ELEMENT A CHARGEE");
                    newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
                }
                else{
                	newChargement = chargementService.updateChargementFirstElement(newChargement, list.stream().sorted((v1, v2) -> v1.getId().compareTo(v2.getId()))
							.findFirst().get().getId());
                	LogisticOperationsMapper logisticOperationsMapper = LogisticOperationsMapper.getInstance();
                    List<ma.inetum.brique.model.principale.LogisticOperationsP> logisticOperationsList = new ArrayList<>();
                    Chargement finalNewChargement = newChargement;
                    list.forEach(element->{
                        ma.inetum.brique.model.principale.LogisticOperationsP logisticOperations = new ma.inetum.brique.model.principale.LogisticOperationsP();
                        logisticOperations = logisticOperationsMapper.transform(logisticOperations, element);
                        logisticOperations.setDateChargement(LocalDateTime.now());
                        logisticOperations.setLoaded(true);
                        logisticOperations.setChargement(finalNewChargement);
                        logisticOperationsList.add(logisticOperations);
                    });

                    logisticOperationsService.saveAll(logisticOperationsList);
                    newChargement.setDateFinChargement(LocalDateTime.now());
                    Long lastMagic = logisticOperationsList.stream().sorted((v1, v2) -> -v1.getMagic().compareTo(v2.getMagic()))
                            .findFirst().get().getMagic();
                    newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.TERMINER_SUCCESS, lastMagic);
                }
            }
            else{
                throw new ExceptionFonctionnelle("Flux inconnu '" + Constantes.FLUX_Logistic_Operations + "'");
            }
        }
        catch(Exception e){
            logger.error("Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_Logistic_Operations + "'", e);
            if ((newChargement != null) && (newChargement.getId() != null)) {
            	newChargement.setErreurs(Arrays.asList(new Erreur(0l, 0l, e.getMessage())));
            	newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.WITH_ERRORS, null);
            }
            throw new ExceptionFonctionnelle(
                    "Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_Logistic_Operations + "'", e);
        }
    }
}
