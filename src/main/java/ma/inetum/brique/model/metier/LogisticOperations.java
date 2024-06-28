package ma.inetum.brique.model.metier;

import ma.inetum.brique.model.pojo.LogisticOperationsPOJO;

import javax.persistence.*;

@Entity
@Table(name = "V_CS_ME_CDMOV")
public class LogisticOperations extends LogisticOperationsPOJO {
    @Id
    @GeneratedValue
    @Column(name = "MAGIC")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
