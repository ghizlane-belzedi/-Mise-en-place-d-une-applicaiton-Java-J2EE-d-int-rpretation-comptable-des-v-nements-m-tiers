package ma.inetum.brique.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationDetailsView {


	private Long id;
	private String dateGeneration;
	private String dateSimuation;
	private String dateArreter;
	private String state;

	private String flux;
	private String fluxCode;
	private List<PieceComptableView> pieces = new ArrayList<>();
	private Map<Long,List<String>> errors = new HashMap<>();
	private ListString resultatGeneration = null;
	private Boolean statut;
	private Boolean enable;
	private Boolean isEmpty;
	private String comment;
	private ListString details;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateGeneration() {
		return dateGeneration;
	}
	public void setDateGeneration(String dateGeneration) {
		this.dateGeneration = dateGeneration;
	}
	public String getDateSimuation() {
		return dateSimuation;
	}
	public void setDateSimuation(String dateSimuation) {
		this.dateSimuation = dateSimuation;
	}
	public String getDateArreter() {
		return dateArreter;
	}
	public void setDateArreter(String dateArreter) {
		this.dateArreter = dateArreter;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFlux() {
		return flux;
	}
	public void setFlux(String flux) {
		this.flux = flux;
	}
	public List<PieceComptableView> getPieces() {
		return pieces;
	}
	public void setPieces(List<PieceComptableView> pieces) {
		this.pieces = pieces;
	}
	public Map<Long, List<String>> getErrors() {
		return errors;
	}
	public void setErrors(Map<Long, List<String>> errors) {
		this.errors = errors;
	}
	public Boolean getStatut() {
		return statut;
	}
	public void setStatut(Boolean statut) {
		this.statut = statut;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Boolean getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ListString getDetails() {
		return details;
	}
	public void setDetails(ListString details) {
		this.details = details;
	}
	public String getFluxCode() {
		return fluxCode;
	}
	public void setFluxCode(String fluxCode) {
		this.fluxCode = fluxCode;
	}
	public ListString getResultatGeneration() {
		return resultatGeneration;
	}
	public void setResultatGeneration(ListString resultatGeneration) {
		this.resultatGeneration = resultatGeneration;
	}
	
}
