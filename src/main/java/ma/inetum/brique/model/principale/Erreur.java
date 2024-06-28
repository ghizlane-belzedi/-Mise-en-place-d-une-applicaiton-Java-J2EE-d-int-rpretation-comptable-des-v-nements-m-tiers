package ma.inetum.brique.model.principale;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LISTE_ERREUR")
public class Erreur {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="LISTE_ERREUR_SEQ")
    @SequenceGenerator(name="LISTE_ERREUR_SEQ", sequenceName="LISTE_ERREUR_SEQ", allocationSize=1)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIMULATION_ID")
	private Simulation simulation;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHARGEMENT_ID")
	private Chargement chargement;
	private Long magic;
	private Long numeroLigne;
	private String description;
	public Erreur(Long magic, Long numeroLigne, String description) {
		super();
		this.magic = magic;
		this.numeroLigne = numeroLigne;
		this.description = description;
	}
	public Erreur() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	public Chargement getChargement() {
		return chargement;
	}
	public void setChargement(Chargement chargement) {
		this.chargement = chargement;
	}
	public Long getMagic() {
		return magic;
	}
	public void setMagic(Long magic) {
		this.magic = magic;
	}
	public Long getNumeroLigne() {
		return numeroLigne;
	}
	public void setNumeroLigne(Long numeroLigne) {
		this.numeroLigne = numeroLigne;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
