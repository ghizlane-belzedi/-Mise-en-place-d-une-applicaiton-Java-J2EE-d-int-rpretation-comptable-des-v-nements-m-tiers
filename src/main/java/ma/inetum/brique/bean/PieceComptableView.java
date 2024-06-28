package ma.inetum.brique.bean;

public class PieceComptableView {

	private Long id;
	private Long numeroPiece;
	private String codeJournale;
	private String date;
	private String description; 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumeroPiece() {
		return numeroPiece;
	}
	public void setNumeroPiece(Long numeroPiece) {
		this.numeroPiece = numeroPiece;
	}
	public String getCodeJournale() {
		return codeJournale;
	}
	public void setCodeJournale(String codeJournale) {
		this.codeJournale = codeJournale;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFlux() {
		return flux;
	}
	public void setFlux(String flux) {
		this.flux = flux;
	}
	public Boolean getStatut() {
		return statut;
	}
	public void setStatut(Boolean statut) {
		this.statut = statut;
	}
	private String flux;
	private Boolean statut;
	
}
