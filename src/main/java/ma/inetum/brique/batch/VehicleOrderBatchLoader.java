package ma.inetum.brique.batch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.VehicleOrderMapper;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.model.principale.VehiculeOrder;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.services.VehiculeOrderService;
import ma.inetum.brique.utilities.Constantes;

@Component
public class VehicleOrderBatchLoader {

	@Autowired
	ParametrageFluxService paramFluxService;

	@Autowired
	ChargementService chargementService;

	@Autowired
	VehiculeOrderService vehicleOrderService;

	private final static Logger logger = LoggerFactory.getLogger(VehicleOrderBatchLoader.class);

	public void loadVehicleOrder() throws ExceptionFonctionnelle {

		Chargement newChargement = null;
		try {

			ParametrageFLux flux = paramFluxService.findFluxByCode(Constantes.FLUX_VEH_ORD);
			if (flux != null) {
				Chargement chargement = chargementService.findLastChargementIdByFluxAndChargementState(flux, ChargementState.TERMINER_SUCCESS);
				Long firstElementToLoad = ((chargement != null)&&(chargement.getLast()!=null)) ? chargement.getLast() + 1l : 1l;

				newChargement = chargementService.initChargement(flux, firstElementToLoad);

				List<ma.inetum.brique.model.metier.VehiculeOrder> list = vehicleOrderService.findAllForBatch(firstElementToLoad);
				if ((list == null) || (list.size() == 0)) {
					logger.info("AUCUNE VEHICULE A CHARGEE");
					newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
				} else {
					newChargement = chargementService.updateChargementFirstElement(newChargement, list.stream().sorted((v1, v2) -> v1.getId().compareTo(v2.getId()))
							.findFirst().get().getId());
					VehicleOrderMapper VOmapper = VehicleOrderMapper.getInstance();
					List<VehiculeOrder> vehicules = new ArrayList<>();
					for (ma.inetum.brique.model.metier.VehiculeOrder v : list.stream().collect(Collectors.toList())) {
						VehiculeOrder vehiculeOrder = new VehiculeOrder();
						vehiculeOrder = VOmapper.transform(vehiculeOrder, v);
						vehiculeOrder.setDateChargement(LocalDateTime.now());
						vehiculeOrder.setLoaded(true);
						vehiculeOrder.setChargement(newChargement);
						vehicules.add(vehiculeOrder);
					}
					Boolean resultat = vehicleOrderService.saveAll(vehicules, newChargement);
					newChargement.setDateFinChargement(LocalDateTime.now());

					Long lastMagic = vehicules.stream().sorted((v1, v2) -> -v1.getMagic().compareTo(v2.getMagic()))
							.findFirst().get().getMagic();
					newChargement = chargementService.updateChargementStatus(newChargement, resultat ? ChargementState.TERMINER_SUCCESS : ChargementState.WITH_ERRORS, resultat ? lastMagic : 0l);
				}

			} else {
				throw new ExceptionFonctionnelle("Flux inconnu '" + Constantes.FLUX_VEH_ORD + "'");
			}

		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_VEH_ORD + "'", e);
			if ((newChargement != null) && (newChargement.getId() != null)) {
				newChargement.setErreurs(Arrays.asList(new Erreur(0l, 0l, e.getMessage())));
				newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.WITH_ERRORS, null);
			}
			throw new ExceptionFonctionnelle(
					"Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_VEH_ORD + "'", e);
		}

	}
}
