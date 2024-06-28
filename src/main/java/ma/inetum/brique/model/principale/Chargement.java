package ma.inetum.brique.model.principale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CHARGEMENT")
public class Chargement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="CHARGEMENT_SEQ")
	@SequenceGenerator(name="CHARGEMENT_SEQ",sequenceName="CHARGEMENT_SEQ", allocationSize=1)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "FLUX_ID")
	private ParametrageFLux flux;
	@Column(name = "DATE_CHARGEMENT")
	private LocalDateTime dateChargement;
	@Column(name = "DATE_FIN_CHARGEMENT")
	private LocalDateTime dateFinChargement;
	@Column(name = "FIRST_MAGIC")
	private Long fisrt;
	@Column(name = "LAST_MAGIC")
	private Long last;
	@Column(name = "STATUS")
	private ChargementState state;
	
	@OneToMany(mappedBy="chargement", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Erreur> erreurs = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ParametrageFLux getFlux() {
		return flux;
	}
	public void setFlux(ParametrageFLux flux) {
		this.flux = flux;
	}
	public LocalDateTime getDateChargement() {
		return dateChargement;
	}
	public void setDateChargement(LocalDateTime dateChargement) {
		this.dateChargement = dateChargement;
	}
	public LocalDateTime getDateFinChargement() {
		return dateFinChargement;
	}
	public void setDateFinChargement(LocalDateTime dateFinChargement) {
		this.dateFinChargement = dateFinChargement;
	}
	public Long getFisrt() {
		return fisrt;
	}
	public void setFisrt(Long fisrt) {
		this.fisrt = fisrt;
	}
	public Long getLast() {
		return last;
	}
	public void setLast(Long last) {
		this.last = last;
	}
	public ChargementState getState() {
		return state;
	}
	public void setState(ChargementState state) {
		this.state = state;
	}
	public List<Erreur> getErreurs() {
		return erreurs;
	}
	public void setErreurs(List<Erreur> erreurs) {
		this.erreurs = erreurs;
	}
}
