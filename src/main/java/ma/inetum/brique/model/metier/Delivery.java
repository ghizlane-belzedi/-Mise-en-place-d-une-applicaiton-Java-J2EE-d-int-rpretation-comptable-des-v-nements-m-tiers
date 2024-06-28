package ma.inetum.brique.model.metier;

import ma.inetum.brique.model.pojo.DeliveryPOJO;

import javax.persistence.*;

@Entity
@Table(name = "V_CS_ME_CDDEL")
public class Delivery extends DeliveryPOJO {

    @Id
    @GeneratedValue
    @Column(name = "MAGIC")
    private Long id;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}
}
