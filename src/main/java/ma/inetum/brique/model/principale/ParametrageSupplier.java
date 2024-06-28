package ma.inetum.brique.model.principale;

import javax.persistence.*;

@Entity
@Table(name = "PARAM_SUPPLIER")
public class ParametrageSupplier {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "CODE_METIER")
    private String codeMedtier;
    @Column(name = "CODE_FINANCE")
    private String codeFinance;
    @Column(name = "DESCRIPTION")
    private String description1;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CURRENCY")
    private String currency;

    //@Column(name = "")
    //private Long ncpt;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeMedtier() {return codeMedtier;}
    public void setCodeMedtier(String codeMedtier) {
        this.codeMedtier = codeMedtier;
    }

    public String getCodeFinance() {
        return codeFinance;
    }
    public void setCodeFinance(String codeFinance) {
        this.codeFinance = codeFinance;
    }

    public String getDescription1() {
        return description1;
    }
    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
