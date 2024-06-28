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
import ma.inetum.brique.mapper.WorkShopMapper;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.model.principale.WorkShopHeader;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.services.WorkShopService;
import ma.inetum.brique.utilities.Constantes;

@Component
public class WorkShopBatchLoader {

	@Autowired
	ParametrageFluxService paramFluxService;

	@Autowired
	ChargementService chargementService;

	@Autowired
	WorkShopService workShopService;
	private final static Logger logger = LoggerFactory.getLogger(WorkShopBatchLoader.class);

	public void loadCosting() throws ExceptionFonctionnelle {

		Chargement newChargement = null;
		try {

			ParametrageFLux flux = paramFluxService.findFluxByCode(Constantes.FLUX_WORKSHOP);
			if (flux != null) {
				Chargement chargement = chargementService.findLastChargementIdByFluxAndChargementState(flux, ChargementState.TERMINER_SUCCESS);
				Long firstElementToLoad = chargement != null ? chargement.getLast() + 1 : 1l;

				newChargement = chargementService.initChargement(flux, firstElementToLoad);

				List<ma.inetum.brique.model.metier.WorkShopHeader> list = workShopService.findAllForBatch(firstElementToLoad);
				if ((list == null) || (list.size() == 0)) {
					logger.info("AUCUN ELEMENT A CHARGEE");
					newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
				} else {
					newChargement = chargementService.updateChargementFirstElement(newChargement, list.stream().sorted((v1, v2) -> v1.getMagic().compareTo(v2.getMagic()))
							.findFirst().get().getMagic());
					WorkShopMapper mapper = WorkShopMapper.getInstance();
					List<WorkShopHeader> costings = new ArrayList<>();
					for (ma.inetum.brique.model.metier.WorkShopHeader v : list) {
						WorkShopHeader costing = new WorkShopHeader();
						costing = mapper.transform(costing, v);
						costing.setDateChargement(LocalDateTime.now());
						costing.setLoaded(true);
						costing.setChargement(newChargement);
						costings.add(costing);
					}
					Boolean resultat = workShopService.saveAll(costings);
					newChargement.setDateFinChargement(LocalDateTime.now());

					Long lastMagic = costings.stream().sorted((v1, v2) -> -v1.getMagic().compareTo(v2.getMagic()))
							.findFirst().get().getMagic();
					newChargement = chargementService.updateChargementStatus(newChargement,resultat ? ChargementState.TERMINER_SUCCESS : ChargementState.WITH_ERRORS, resultat ? lastMagic : 0l);
				}

			} else {
				throw new ExceptionFonctionnelle("Flux inconnu '" + Constantes.FLUX_WORKSHOP + "'");
			}

		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_WORKSHOP + "'", e);
			if ((newChargement != null) && (newChargement.getId() != null)) {
				newChargement.setErreurs(Arrays.asList(new Erreur(0l, 0l, e.getMessage())));
				newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.WITH_ERRORS, null);
				
			}
			throw new ExceptionFonctionnelle(
					"Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_WORKSHOP + "'", e);
		}

	}
}
