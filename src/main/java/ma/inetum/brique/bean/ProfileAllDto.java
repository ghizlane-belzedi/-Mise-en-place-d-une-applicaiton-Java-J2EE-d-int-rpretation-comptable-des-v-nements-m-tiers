package ma.inetum.brique.bean;

public class ProfileAllDto {

	private String code;
	private String description;
	private Integer nombreUsers;
    private Boolean enabled;
	private Long id;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNombreUsers() {
		return nombreUsers;
	}
	public void setNombreUsers(Integer nombreUsers) {
		this.nombreUsers = nombreUsers;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
