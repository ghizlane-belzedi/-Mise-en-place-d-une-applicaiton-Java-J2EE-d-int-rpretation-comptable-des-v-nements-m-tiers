package ma.inetum.brique.model.principale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "APP_USER", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "USER_NAME") })
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NAME", length = 36, nullable = false)
    private String userName;

    @Column(name = "ENCRYTED_PASSWORD", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "ENABLED", length = 1, nullable = false)
    private Boolean enabled;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PROFILE")
	private AppProfile profile;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "App_USER_FLUX",
    			joinColumns = @JoinColumn( name = "ID_USER" ),
                inverseJoinColumns = @JoinColumn( name = "ID_FLUX" )  )
    private Set<ParametrageFLux> flux = new HashSet<>();
//    @OneToMany
//    List<AppUserRole> roles = new ArrayList<>(); 
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
	public AppProfile getProfile() {
		return profile;
	}
	public void setProfile(AppProfile profile) {
		this.profile = profile;
	}
	public Set<ParametrageFLux> getFlux() {
		return flux;
	}
	public void setFlux(Set<ParametrageFLux> flux) {
		this.flux = flux;
	}
	
}