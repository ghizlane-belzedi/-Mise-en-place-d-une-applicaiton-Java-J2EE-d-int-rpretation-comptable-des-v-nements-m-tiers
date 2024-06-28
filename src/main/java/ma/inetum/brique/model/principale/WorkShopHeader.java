package ma.inetum.brique.model.principale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ma.inetum.brique.model.pojo.WorkShopHeaderPOJO;

@Entity
@Table(name = "CS_ME_CDPOH")
public class WorkShopHeader extends WorkShopHeaderPOJO {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "MAGIC")
	private Long magic;
	@ManyToOne
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
	@OneToMany(mappedBy="header", cascade = CascadeType.ALL)
	private List<WorkShopDetails> details = new ArrayList<>();
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
	public Chargement getChargement() {
		return chargement;
	}
	public void setChargement(Chargement chargement) {
		this.chargement = chargement;
	}
	public LocalDateTime getDateChargement() {
		return dateChargement;
	}
	public void setDateChargement(LocalDateTime dateChargement) {
		this.dateChargement = dateChargement;
	}
	public Boolean getLoaded() {
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
	public Boolean getSent() {
		return sent;
	}
	public void setSent(Boolean sent) {
		this.sent = sent;
	}
	public List<WorkShopDetails> getDetails() {
		return details;
	}
	public void setDetails(List<WorkShopDetails> details) {
		this.details = details;
	}


}
