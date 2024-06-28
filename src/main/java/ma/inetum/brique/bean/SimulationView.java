package ma.inetum.brique.bean;

public class SimulationView {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateGeneration() {
		return dateGeneration;
	}

	public void setDateGeneration(String dateGeneration) {
		this.dateGeneration = dateGeneration;
	}

	public String getDateSimuation() {
		return dateSimuation;
	}

	public void setDateSimuation(String dateSimuation) {
		this.dateSimuation = dateSimuation;
	}

	public String getDateArreter() {
		return dateArreter;
	}

	public void setDateArreter(String dateArreter) {
		this.dateArreter = dateArreter;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFlux() {
		return flux;
	}

	public void setFlux(String flux) {
		this.flux = flux;
	}

	private Long id;
	private String dateGeneration;
	private String dateSimuation;
	private String dateArreter;
	private String state;

	private String flux;
}
