package ma.inetum.brique.model.principale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "SIMULATION")
public class Simulation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="SIMULATION_SEQ")
    @SequenceGenerator(name="SIMULATION_SEQ", sequenceName="SIMULATION_SEQ", allocationSize=1)
	private Long id;
	private LocalDateTime dateGeneration;
	private LocalDateTime dateSimuation;
	private LocalDate  dateArreter;
	private String username;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDateGeneration() {
		return dateGeneration;
	}
	public void setDateGeneration(LocalDateTime dateGeneration) {
		this.dateGeneration = dateGeneration;
	}
	public LocalDateTime getDateSimuation() {
		return dateSimuation;
	}
	public void setDateSimuation(LocalDateTime dateSimuation) {
		this.dateSimuation = dateSimuation;
	}
	public LocalDate getDateArreter() {
		return dateArreter;
	}
	public void setDateArreter(LocalDate dateArreter) {
		this.dateArreter = dateArreter;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public SimulationState getState() {
		return state;
	}
	public void setState(SimulationState state) {
		this.state = state;
	}
	public ParametrageFLux getFlux() {
		return flux;
	}
	public void setFlux(ParametrageFLux flux) {
		this.flux = flux;
	}
	public List<PieceComptable> getPieces() {
		return pieces;
	}
	public void setPieces(List<PieceComptable> pieces) {
		this.pieces = pieces;
	}
	public List<Erreur> getErreurs() {
		return erreurs;
	}
	public void setErreurs(List<Erreur> erreurs) {
		this.erreurs = erreurs;
	}
	private SimulationState state;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLUX_ID")
	private ParametrageFLux flux;
	
	@OneToMany(mappedBy="simulation", cascade = CascadeType.ALL)
	private List<PieceComptable> pieces = new ArrayList<>();
	@OneToMany(mappedBy="simulation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Erreur> erreurs = new ArrayList<>();
}
