package ma.inetum.brique.batch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.DeliveryMapper;
import ma.inetum.brique.model.metier.Delivery;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.DeliveryP;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.DeliveryService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.utilities.Constantes;

@Component
public class DeliveryBatchLoader {
    @Autowired
    ParametrageFluxService paramFluxService;
    @Autowired
    ChargementService chargementService;
    @Autowired
    DeliveryService deliveryService;

    private final static Logger logger = LoggerFactory.getLogger(DeliveryBatchLoader.class);
    public void loadDelivery() throws ExceptionFonctionnelle {
        Chargement newChargement = null;
        try {
            ParametrageFLux flux = paramFluxService.findFluxByCode(Constantes.FLUX_DELIVERY);
            if (flux != null) {
                Chargement chargement = chargementService.findLastChargementIdByFluxAndChargementState(flux, ChargementState.TERMINER_SUCCESS);
                Long firstElementToLoad = ((chargement != null)&&(chargement.getLast()!=null)) ? chargement.getLast() + 1l : 1l;
                newChargement = chargementService.initChargement(flux, firstElementToLoad);
                List<Delivery> list = deliveryService.findAllForBatch(firstElementToLoad);
                if ((list == null) || (list.size() == 0)) {
                    logger.info("AUCUN ELEMENT A CHARGEE");
                    newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
                }
                else {
                	newChargement = chargementService.updateChargementFirstElement(newChargement, list.stream().sorted((v1, v2) -> v1.getId().compareTo(v2.getId()))
							.findFirst().get().getId());
                	DeliveryMapper deliveryMapper = DeliveryMapper.getInstance();
                    List<DeliveryP> deliveryPList = new ArrayList<>();
                    Chargement finalNewChargement = newChargement;
                    list.forEach(element->{
                        DeliveryP deliveryP = new DeliveryP();
                        deliveryP = deliveryMapper.transform(deliveryP, element);
                        deliveryP.setDateChargement(LocalDateTime.now());
                        deliveryP.setLoaded(true);
                        deliveryP.setChargement(finalNewChargement);
                        deliveryPList.add(deliveryP);
                    });

                    deliveryService.saveAll(deliveryPList);
                    newChargement.setDateFinChargement(LocalDateTime.now());
                    Long lastMagic = deliveryPList.stream().sorted((v1, v2) -> -v1.getMagic().compareTo(v2.getMagic()))
                            .findFirst().get().getMagic();
                    newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.TERMINER_SUCCESS, lastMagic);
                }
            }
            else{
                throw new ExceptionFonctionnelle("Flux inconnu '" + Constantes.FLUX_DELIVERY + "'");
            }
        }
        catch(Exception e){
            logger.error("Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_DELIVERY + "'", e);
            if ((newChargement != null) && (newChargement.getId() != null)) {
            	newChargement.setErreurs(Arrays.asList(new Erreur(0l, 0l, e.getMessage())));
            	newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.WITH_ERRORS, null);
            }
            throw new ExceptionFonctionnelle(
                    "Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_DELIVERY + "'", e);
        }
    }
}
