package ma.inetum.brique.bean;

public class ChargementView {

	private Long id;
	private String dateChargement;
	private String dateFinChargement;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEnvoye() {
		return envoye;
	}
	public void setEnvoye(String envoye) {
		this.envoye = envoye;
	}
	public String getFlux() {
		return flux;
	}
	public void setFlux(String flux) {
		this.flux = flux;
	}
	public Long getLast() {
		return last;
	}
	public void setLast(Long last) {
		this.last = last;
	}

	private Long fisrt;
	private Long last;
	private Long totalCharged ;
	private String state;
	private String envoye;
	private String flux;
}
