package ma.inetum.brique.model.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ma.inetum.brique.model.pojo.InvoicePOJO;

@Entity
@Table(name = "V_CS_ME_CDINV")
public class Invoice extends InvoicePOJO {
	@Id
	@Column(name = "MAGIC")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 

}
