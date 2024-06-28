package ma.inetum.brique.model.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ma.inetum.brique.model.pojo.CostingHeaderPOJO;

@Entity
@Table(name = "V_CS_ME_CDCOH")
public class CostingHeader  extends CostingHeaderPOJO{
	@Id
	@Column(name = "MAGIC")
	private Long magic;
	
	@OneToMany(mappedBy="header", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CostingDetails> details = new ArrayList<>();
	
	public Long getMagic() {
		return magic;
	}

	public void setMagic(Long magic) {
		this.magic = magic;
	}

	public List<CostingDetails> getDetails() {
		return details;
	}

	public void setDetails(List<CostingDetails> details) {
		this.details = details;
	}
	

}
