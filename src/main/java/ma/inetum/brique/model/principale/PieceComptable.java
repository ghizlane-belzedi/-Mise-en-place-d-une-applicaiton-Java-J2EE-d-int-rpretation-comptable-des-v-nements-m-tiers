package ma.inetum.brique.model.principale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PIECE_COMPTABLE")
public class PieceComptable implements Cloneable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="PIECE_COMPTABLE_SEQ")
    @SequenceGenerator(name="PIECE_COMPTABLE_SEQ", sequenceName="PIECE_COMPTABLE_SEQ", allocationSize=1)
	private Long id;
	private String codeJournale;
	private Long numeroPiece;
	private LocalDate date;
	@Column(name = "ENTETE_DOC")
	private String enteteDoc;
	@Column(name = "SENT")
	private Boolean sent;
	@ManyToOne
	@JoinColumn(name = "FLUX_ID")
	private ParametrageFLux flux;
	@ManyToOne
	@JoinColumn(name = "SIMULATION_ID")
	private Simulation simulation;
	@OneToMany(mappedBy="piece", cascade = CascadeType.ALL)
	private List<EcritureComptable> ecritures = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodeJournale() {
		return codeJournale;
	}
	public void setCodeJournale(String codeJournale) {
		this.codeJournale = codeJournale;
	}
	public Long getNumeroPiece() {
		return numeroPiece;
	}
	public void setNumeroPiece(Long numeroPiece) {
		this.numeroPiece = numeroPiece;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getEnteteDoc() {
		return enteteDoc;
	}
	public void setEnteteDoc(String enteteDoc) {
		this.enteteDoc = enteteDoc;
	}
	public Boolean getSent() {
		return sent;
	}
	public void setSent(Boolean sent) {
		this.sent = sent;
	}
	public ParametrageFLux getFlux() {
		return flux;
	}
	public void setFlux(ParametrageFLux flux) {
		this.flux = flux;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	public List<EcritureComptable> getEcritures() {
		return ecritures;
	}
	public void setEcritures(List<EcritureComptable> ecritures) {
		this.ecritures = ecritures;
	}
	public PieceComptable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PieceComptable(LocalDate date, String enteteDoc, ParametrageFLux flux, Simulation simulation) {
		super();
		this.date = date;
		this.enteteDoc = enteteDoc;
		this.flux = flux;
		this.simulation = simulation;
	}

//	@ManyToOne
//	@JoinColumn(name = "VEHICULE_ID")
//	private VehiculeOrder vehicule;
	@Override
	public Object clone() throws CloneNotSupportedException {
		PieceComptable pieceComptable = new PieceComptable();
		pieceComptable.setDate(date);
		pieceComptable.setEnteteDoc(enteteDoc);
		pieceComptable.setFlux(flux);
		pieceComptable.setSimulation(simulation);
		return pieceComptable;
	}

	
}
