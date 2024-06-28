package ma.inetum.brique.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import ma.inetum.brique.bean.ChargementDetailsView;
import ma.inetum.brique.bean.ChargementView;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.Chargement;
import ma.inetum.brique.model.principale.ChargementState;
import ma.inetum.brique.model.principale.ParametrageFLux;

public interface ChargementService {

	public ChargementDetailsView findById(Long id) ;

	public List<ChargementView> getAll();
	public DataTablesOutput<ChargementView> getAll(DataTablesInput input, List<String> flux);

	public Chargement findLastChargementByFlux(ParametrageFLux flux, Pageable page);
	public Chargement getOne(Long lastChargementId);

	public Chargement findLastChargementIdByFluxAndChargementState(ParametrageFLux flux, ChargementState state);
	public Chargement initChargement(ParametrageFLux flux, Long firstElementToLoad) throws ExceptionFonctionnelle;
	public Chargement updateChargementFirstElement(Chargement chargement, Long firstElement) throws ExceptionFonctionnelle;
	public Chargement updateChargementStatus(Chargement chargement, ChargementState state, Long lastMagic) throws ExceptionFonctionnelle ;
	public List<ChargementView> situationChargement();
	public String launch( String f, List<String> flux);
}
