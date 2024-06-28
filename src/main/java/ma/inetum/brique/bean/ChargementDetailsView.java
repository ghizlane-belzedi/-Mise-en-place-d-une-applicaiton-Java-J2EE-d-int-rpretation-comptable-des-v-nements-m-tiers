package ma.inetum.brique.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChargementDetailsView {

	private Long id;
	private String dateChargement;
	private String dateFinChargement;
	private Long fisrt;
	private Long totalCharged ;

	private String flux;
	private String fluxCode;
	private String state;
	private Boolean statut;
	private Map<Long,List<String>> errors = new HashMap<>();
	private ListString details;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateChargement() {
		return dateChargement;
	}
	public void setDateChargement(String dateChargement) {
		this.dateChargement = dateChargement;
	}
	public String getDateFinChargement() {
		return dateFinChargement;
	}
	public void setDateFinChargement(String dateFinChargement) {
		this.dateFinChargement = dateFinChargement;
	}
	public Long getFisrt() {
		return fisrt;
	}
	public void setFisrt(Long fisrt) {
		this.fisrt = fisrt;
	}
	public Long getTotalCharged() {
		return totalCharged;
	}
	public void setTotalCharged(Long totalCharged) {
		this.totalCharged = totalCharged;
	}
	public String getFlux() {
		return flux;
	}
	public void setFlux(String flux) {
		this.flux = flux;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getStatut() {
		return statut;
	}
	public void setStatut(Boolean statut) {
		this.statut = statut;
	}
	public Map<Long, List<String>> getErrors() {
		return errors;
	}
	public void setErrors(Map<Long, List<String>> errors) {
		this.errors = errors;
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
}
