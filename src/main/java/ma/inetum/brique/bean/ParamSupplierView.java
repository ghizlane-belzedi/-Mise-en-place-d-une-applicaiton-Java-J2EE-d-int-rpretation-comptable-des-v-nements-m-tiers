package ma.inetum.brique.bean;

import javax.validation.constraints.NotEmpty;

public class ParamSupplierView {

    private Long id;
    @NotEmpty(message = "Code Métier est obligatoire")
    private String codeMedtier;
    @NotEmpty(message = "Code Finance est obligatoire")
    private String codeFinance;
    
    private String description1;
    @NotEmpty(message = ""
    		+ "Type est obligatoire")
    private String type;
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeMedtier() {
        return codeMedtier;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
