package ma.inetum.brique.bean;

import java.util.HashSet;
import java.util.Set;

import ma.inetum.brique.model.principale.AppProfile;
import ma.inetum.brique.model.principale.AppRole;

public class ProfilBean {
	private Set<String> roles = new HashSet<>();
	private Long id;
    private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
    
	public static void entityToBean(AppProfile profil, ProfilBean bean) {
		bean.setId(profil.getId());
    	bean.setName(profil.getNom());
    	for(AppRole role : profil.getRoles()) {
			bean.getRoles().add(role.getRoleName());
		}
    }
	public static void beanToEntity(AppProfile profil, ProfilBean bean) {
		profil.setId(bean.getId());
//		profil.setRoleName(bean.getName());
    }
}
