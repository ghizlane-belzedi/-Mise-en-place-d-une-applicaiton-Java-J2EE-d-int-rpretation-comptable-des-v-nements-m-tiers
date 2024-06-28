package ma.inetum.brique.model.metier;

import ma.inetum.brique.model.pojo.VehicleFinalisationHeaderPOJO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "V_CS_ME_CDFIH")
public class VehicleFinalisationHeader extends VehicleFinalisationHeaderPOJO {
    @Id
    @GeneratedValue
    @Column(name = "MAGIC")
    private Long id;
    @OneToMany(mappedBy="vehicleFinalisationHeader", cascade = CascadeType.ALL)
    private List<VehicleFinalisationDetails> details = new ArrayList<>();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public List<VehicleFinalisationDetails> getDetails() {
        return details;
    }

    public void setDetails(List<VehicleFinalisationDetails> details) {
        this.details = details;
    }
}
