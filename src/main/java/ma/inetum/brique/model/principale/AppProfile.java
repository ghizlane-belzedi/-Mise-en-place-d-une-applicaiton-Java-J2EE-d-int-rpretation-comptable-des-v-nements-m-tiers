package ma.inetum.brique.model.principale;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "APP_PROFILE")
public class AppProfile {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String code;
	@Column(name = "Enabled", length = 1, nullable = false)
    private Boolean enabled;
	
	@OneToMany(mappedBy="profile", cascade = CascadeType.ALL)
	private List<AppUser> users;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "APP_PROFILES_ROLES",
    			joinColumns = @JoinColumn( name = "idProfile" ),
                inverseJoinColumns = @JoinColumn( name = "idRole" )  )
    private List<AppRole> roles = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<AppUser> getUsers() {
		return users;
	}
	public void setUsers(List<AppUser> users) {
		this.users = users;
	}
	public List<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}
}
