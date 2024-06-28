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
import ma.inetum.brique.mapper.CostingMapper;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.CostingHeader;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.model.principale.ParametrageFLux;
import ma.inetum.brique.services.ChargementService;
import ma.inetum.brique.services.CostingService;
import ma.inetum.brique.services.ParametrageFluxService;
import ma.inetum.brique.utilities.Constantes;

@Component
public class CostingBatchLoader {

	@Autowired
	ParametrageFluxService paramFluxService;

	@Autowired
	ChargementService chargementService;

	@Autowired
	CostingService costingService;

	private final static Logger logger = LoggerFactory.getLogger(CostingBatchLoader.class);

	public void loadCosting() throws ExceptionFonctionnelle {

		Chargement newChargement = null;
		try {

			ParametrageFLux flux = paramFluxService.findFluxByCode(Constantes.FLUX_COSTING);
			if (flux != null) {
				Chargement chargement = chargementService.findLastChargementIdByFluxAndChargementState(flux, ChargementState.TERMINER_SUCCESS);
				Long firstElementToLoad = chargement != null ? chargement.getLast() + 1 : 1l;

				newChargement = chargementService.initChargement(flux, firstElementToLoad);

				List<ma.inetum.brique.model.metier.CostingHeader> list = costingService.findAllForBatch(firstElementToLoad);
				if ((list == null) || (list.size() == 0)) {
					logger.info("AUCUN COSTING A CHARGEE");
					newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.EMPTY_LIST, null);
				} else {
					newChargement = chargementService.updateChargementFirstElement(newChargement, list.stream().sorted((v1, v2) -> v1.getMagic().compareTo(v2.getMagic()))
							.findFirst().get().getMagic());
					CostingMapper mapper = CostingMapper.getInstance();
					List<CostingHeader> costings = new ArrayList<>();
					for (ma.inetum.brique.model.metier.CostingHeader v : list.stream().collect(Collectors.toList())) {
						CostingHeader costing = new CostingHeader();
						costing = mapper.transform(costing, v);
						costing.setDateChargement(LocalDateTime.now());
						costing.setLoaded(true);
						costing.setChargement(newChargement);
						costings.add(costing);
					}
					Boolean resultat = costingService.saveAll(costings);
					newChargement.setDateFinChargement(LocalDateTime.now());

					Long lastMagic = costings.stream().sorted((v1, v2) -> -v1.getMagic().compareTo(v2.getMagic()))
							.findFirst().get().getMagic();
					newChargement = chargementService.updateChargementStatus(newChargement, resultat ? ChargementState.TERMINER_SUCCESS : ChargementState.WITH_ERRORS, resultat ? lastMagic : 0l);
				}

			} else {
				throw new ExceptionFonctionnelle("Flux inconnu '" + Constantes.FLUX_COSTING + "'");
			}

		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_COSTING + "'", e);
			if ((newChargement != null) && (newChargement.getId() != null)) {
				newChargement.setErreurs(Arrays.asList(new Erreur(0l, 0l, e.getMessage())));
				newChargement = chargementService.updateChargementStatus(newChargement, ChargementState.WITH_ERRORS, null);
			}
			throw new ExceptionFonctionnelle(
					"Une erreur s'est produite lors chargement du flux '" + Constantes.FLUX_COSTING + "'", e);
		}

	}
}
