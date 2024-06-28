package ma.inetum.brique.bean;

import java.util.ArrayList;
import java.util.List;


public class PieceComptableDetailsView {

	public static class EcritureComptableView {
		private Long id;
		private String ncpt;
		private String costCenter;
		private String accountDescription;
		private String montant;
		private String montantMAD;
		private String sens;
		private String ref1;
		private String ref2;
		private String ref3;
		private String ref4;
		private String ref5;
		private String ref6;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNcpt() {
			return ncpt;
		}
		public void setNcpt(String ncpt) {
			this.ncpt = ncpt;
		}
		public String getCostCenter() {
			return costCenter;
		}
		public void setCostCenter(String costCenter) {
			this.costCenter = costCenter;
		}
		public String getAccountDescription() {
			return accountDescription;
		}
		public void setAccountDescription(String accountDescription) {
			this.accountDescription = accountDescription;
		}
		public String getMontant() {
			return montant;
		}
		public void setMontant(String montant) {
			this.montant = montant;
		}
		public String getMontantMAD() {
			return montantMAD;
		}
		public void setMontantMAD(String montantMAD) {
			this.montantMAD = montantMAD;
		}
		public String getSens() {
			return sens;
		}
		public void setSens(String sens) {
			this.sens = sens;
		}
		public String getRef1() {
			return ref1;
		}
		public void setRef1(String ref1) {
			this.ref1 = ref1;
		}
		public String getRef2() {
			return ref2;
		}
		public void setRef2(String ref2) {
			this.ref2 = ref2;
		}
		public String getRef3() {
			return ref3;
		}
		public void setRef3(String ref3) {
			this.ref3 = ref3;
		}
		public String getRef4() {
			return ref4;
		}
		public void setRef4(String ref4) {
			this.ref4 = ref4;
		}
		public String getRef5() {
			return ref5;
		}
		public void setRef5(String ref5) {
			this.ref5 = ref5;
		}
		public String getRef6() {
			return ref6;
		}
		public void setRef6(String ref6) {
			this.ref6 = ref6;
		}
		
	}
	private Long id;
	private Long numeroPiece;
	private String codeJournale;
	private String date;
	private String description; 
	private String flux;
	private String fluxCode;
	private Boolean statut;
	private List<EcritureComptableView> ecritures = new ArrayList<>();
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
	public List<EcritureComptableView> getEcritures() {
		return ecritures;
	}
	public void setEcritures(List<EcritureComptableView> ecritures) {
		this.ecritures = ecritures;
	}
	public String getFluxCode() {
		return fluxCode;
	}
	public void setFluxCode(String fluxCode) {
		this.fluxCode = fluxCode;
	}
	
}
