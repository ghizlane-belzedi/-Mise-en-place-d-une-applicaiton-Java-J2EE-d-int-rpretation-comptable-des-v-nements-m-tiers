package ma.inetum.brique.model.principale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "APP_ROLE", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
public class AppRole {

	@Id
	@GeneratedValue
	@Column(name = "Role_Id", nullable = false)
	private Long roleId;

	@Column(name = "Role_Name", length = 30, nullable = false)
	private String roleName;
	private String module;
	private String controller;
	private String action;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	} 
	
}