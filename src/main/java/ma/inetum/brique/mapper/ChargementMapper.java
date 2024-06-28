package ma.inetum.brique.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ma.inetum.brique.bean.ChargementDetailsView;
import ma.inetum.brique.bean.ChargementView;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.utilities.Constantes;

public class ChargementMapper {
	private static ChargementMapper mapper;

	private ChargementMapper() {
	}

	public static ChargementMapper getInstance() {

		// create object if it's not already created
		if (mapper == null) {
			mapper = new ChargementMapper();
		}

		// returns the singleton object
		return mapper;
	}

	public ChargementView chargementToChargementView(Chargement c) {
		ChargementView chargement = new ChargementView();
		chargement.setId(c.getId());
		chargement.setFlux(c.getFlux().getNom());
		chargement.setDateChargement(c.getDateChargement() != null ? c.getDateChargement().format(Constantes.FORMAT_DATE_TIME) : "");
		chargement.setDateFinChargement(c.getDateFinChargement() != null ? c.getDateFinChargement().format(Constantes.FORMAT_DATE_TIME) : "");
		chargement.setFisrt(c.getFisrt());
		chargement.setLast(c.getLast() != null ? c.getLast() : -1); 
		chargement.setTotalCharged(c.getLast() != null ? c.getLast() - c.getFisrt() + 1 : 0l);
		chargement.setState(c.getState().name());
		return chargement;
	}

	public ChargementDetailsView chargementToChargementDetailsView(Chargement c) {
		ChargementDetailsView chargement = new ChargementDetailsView();
		chargement.setId(c.getId());
		chargement.setFlux(c.getFlux().getNom());
		chargement.setFluxCode(c.getFlux().getCode());
		chargement.setDateChargement(c.getDateChargement() != null ? c.getDateChargement().format(Constantes.FORMAT_DATE_TIME) : "");
		chargement.setDateFinChargement(c.getDateFinChargement() != null ?  c.getDateFinChargement().format(Constantes.FORMAT_DATE_TIME) : "");
		chargement.setFisrt(c.getFisrt());
		chargement.setTotalCharged(c.getLast() != null ? c.getLast() - c.getFisrt() + 1 : 0l);
		chargement.setState(c.getState().name());
		Map<Long, List<String>> map = new TreeMap<>();
		for(Erreur e : c.getErreurs()) {
			if(map.get(e.getNumeroLigne()) == null) {
				map.put(e.getNumeroLigne(), new ArrayList<>());
			}
			map.get(e.getNumeroLigne()).add(e.getDescription());
		}
//		errors.put(1l, List.of("Fournisseur n'existe pas", "Marque absente", "Erreur de montant"));
//		errors.put(2l, List.of("Fournisseur n'existe pas"));
//		errors.put(3l, List.of("Marque absente", "Erreur de montant"));
		chargement.setErrors(map);
		chargement.setStatut(c.getErreurs().isEmpty() );
		return chargement;
	}
}
