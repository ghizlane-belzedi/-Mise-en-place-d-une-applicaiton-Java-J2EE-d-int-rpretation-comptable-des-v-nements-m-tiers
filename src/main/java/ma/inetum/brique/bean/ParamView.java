package ma.inetum.brique.bean;


public class ParamView {

    private Long ParamId;
    private String categorie;
    private String codeMedtier;
    private String codeFinance;
    private String description1;
    private String addtionalField;
    private String addtionalFieldDescr;

    public Long getParamId() {
        return ParamId;
    }
    public void setParamId(Long paramId) {
        ParamId = paramId;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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

    public String getAddtionalField() {
        return addtionalField;
    }

    public void setAddtionalField(String addtionalField) {
        this.addtionalField = addtionalField;
    }

    public String getAddtionalFieldDescr() {
        return addtionalFieldDescr;
    }

    public void setAddtionalFieldDescr(String addtionalFieldDescr) {
        this.addtionalFieldDescr = addtionalFieldDescr;
    }
}
