package ma.inetum.brique.model.principale;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import ma.inetum.brique.model.pojo.InvoicePOJO;

@Entity
@Table(name = "CS_ME_CDINV")
@DynamicUpdate

public class Invoice  extends InvoicePOJO {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "MAGIC")
	private Long magic;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CHARGEMENT")
	private Chargement chargement;
	
	@Column(name = "DATE_CHARGEMENT")
	private LocalDateTime dateChargement;
	
	@Column(name = "FLAG_CHARGE")
	private Boolean loaded;
	
	@Column(name = "DATE_ENVOI")
	private LocalDateTime dateEnvoi;
	
	@Column(name = "FLAG_ENVOI")
	private Boolean sent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMagic() {
		return magic;
	}
	public void setMagic(Long magic) {
		this.magic = magic;
	}
	public LocalDateTime getDateChargement() {
		return dateChargement;
	}
	public void setDateChargement(LocalDateTime dateChargement) {
		this.dateChargement = dateChargement;
	}
	public Boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}
	public LocalDateTime getDateEnvoi() {
		return dateEnvoi;
	}
	public void setDateEnvoi(LocalDateTime dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}
	public Boolean isSent() {
		return sent;
	}
	public void setSent(Boolean sent) {
		this.sent = sent;
	}

	public Chargement getChargement() {
		return chargement;
	}
	public void setChargement(Chargement chargement) {
		this.chargement = chargement;
	}

	
}
