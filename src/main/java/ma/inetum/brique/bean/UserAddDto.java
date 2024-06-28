package ma.inetum.brique.bean;



import javax.validation.constraints.NotEmpty;

public class UserAddDto {

	private Long userId;

	@NotEmpty(message = "userName est obligatoire")
    private String userName;
	
    private String encrytedPassword;
    private Boolean enabled;
    @NotEmpty(message = "nom est obligatoire")
    private String nom;
    private String prenom;
    private Long profile;
    @NotEmpty(message = "password est obligatoire")
    private String password;
    
    private String repassword;
    private String[] flux;
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getProfile() {
		return profile;
	}

	public void setProfile(Long profile) {
		this.profile = profile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String[] getFlux() {
		return flux;
	}

	public void setFlux(String[] flux) {
		this.flux = flux;
	}
}
