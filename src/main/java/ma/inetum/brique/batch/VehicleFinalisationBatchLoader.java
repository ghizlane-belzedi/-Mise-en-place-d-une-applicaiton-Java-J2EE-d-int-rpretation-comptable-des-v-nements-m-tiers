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
import ma.inetum.brique.mapper.VehicleFinalisationMapper;
import ma.inetum.brique.model.metier.VehicleFinalisationDetails;
import ma.inetum.brique.model.metier.VehicleFinalisationHeader;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.model.principale.VehicleFinalisationHeaderP;
import ma.inetum.brique.repository.metier.VehicleFinalisationHeaderRepository;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.services.VehicleFinalisationService;
import ma.inetum.brique.utilities.Constantes;

@Component
public class VehicleFinalisationBatchLoader {

    @Autowired
    ParametrageFluxService paramFluxService;

    @Autowired
    ChargementService chargementService;

    @Autowired
    VehicleFinalisationService vehicleFinalisationService;
    @Autowired
    private VehicleFinalisationHeaderRepository vehicleFinalisationHeaderRepository;
    private final static Logger logger = LoggerFactory.getLogger(VehicleFinalisationBatchLoader.class);
    public void loadVehicleFinalisation() throws ExceptionFonctionnelle {
        Chargement newChargement = null;
        try{
            ParametrageFLux flux = paramFluxService.findFluxByCode(Constantes.FLUX_VEH_FIN);
            if (flux != null) {
                Chargement chargement = chargementService.findLastChargementIdByFluxAndChargementState(flux, ChargementState.TERMINER_SUCCESS);
                Long firstElementToLoad = ((chargement != null)&&(chargement.getLast()!=null)) ? chargement.getLast() + 1 : 1l;
                newChargement = chargementService.initChargement(flux, firstElementToLoad);
                List<VehicleFinalisationHeader> listHeader =  vehicleFinalisationService.findAllForBatch(firstElementToLoad);
                if ((listHeader == null) || (listHeader.size() == 0)) {
                    logger.info("AUCUNE VEHICULE A CHARGEE");
                    newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
                }
                else{
                	newChargement = chargementService.updateChargementFirstElement(newChargement, listHeader.stream().sorted((v1, v2) -> v1.getId().compareTo(v2.getId()))
							.findFirst().get().getId());
                	VehicleFinalisationMapper mapper = VehicleFinalisationMapper.getInstance();
                    List<VehicleFinalisationHeaderP> vehicleFinalisationHeaderPList = new ArrayList<>();
                    Chargement finalNewChargement = newChargement;
                    final boolean[] detailsExist = {true};
                    listHeader.forEach(header->{
                        List<VehicleFinalisationDetails> details = vehicleFinalisationHeaderRepository.findDetails(header.getId());
                        if(details == null || details.size() == 0){
                            detailsExist[0] = false;
                        }
                        else{
                            header.setDetails(details);
                            VehicleFinalisationHeaderP vehicleFinalisationHeaderP = new VehicleFinalisationHeaderP();
                            vehicleFinalisationHeaderP =  mapper.transform(vehicleFinalisationHeaderP, header);

                            vehicleFinalisationHeaderP.setDateChargement(LocalDateTime.now());
                            vehicleFinalisationHeaderP.setLoaded(true);
                            vehicleFinalisationHeaderP.setChargement(finalNewChargement);
                            vehicleFinalisationHeaderPList.add(vehicleFinalisationHeaderP);
                        }
                    });
                    if(detailsExist[0] == true){
                        vehicleFinalisationService.saveAll(vehicleFinalisationHeaderPList);

                        newChargement.setDateFinChargement(LocalDateTime.now());
                        Long lastMagic = vehicleFinalisationHeaderPList.stream().sorted((v1, v2) -> -v1.getMagic().compareTo(v2.getMagic()))
                                .findFirst().get().getMagic();
                        newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.TERMINER_SUCCESS, lastMagic);
                    }
                    else{
                        logger.info("Un(des) entete(s) est(sont) sans details.");
                        newChargement.setDateFinChargement(LocalDateTime.now());
                        newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
                    }
                }
            }
            else {
                throw new ExceptionFonctionnelle("Flux inconnu '" + Constantes.FLUX_VEH_FIN + "'");
            }
        }
        catch(Exception e){
            logger.error("Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_VEH_FIN + "'", e);
            if ((newChargement != null) && (newChargement.getId() != null)) {
            	newChargement.setErreurs(Arrays.asList(new Erreur(0l, 0l, e.getMessage())));
            	newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.WITH_ERRORS, null);
            }
            throw new ExceptionFonctionnelle(
                    "Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_VEH_FIN + "'", e);
        }
    }
}
