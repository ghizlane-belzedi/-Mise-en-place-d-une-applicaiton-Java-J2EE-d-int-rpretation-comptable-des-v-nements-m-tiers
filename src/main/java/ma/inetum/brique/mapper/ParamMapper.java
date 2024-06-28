package ma.inetum.brique.mapper;

import ma.inetum.brique.bean.ParamView;
import ma.inetum.brique.model.principale.Parametrage;

public class ParamMapper {

    private static ParamMapper mapper;

    private ParamMapper() {
    }

    public static ParamMapper getInstance() {

        // create object if it's not already created
        if (mapper == null) {
            mapper = new ParamMapper();
        }

        // returns the singleton object
        return mapper;
    }

    public ParamView paramToParamView(Parametrage p) {
        ParamView paramView = new ParamView();
        paramView.setParamId(p.getId());
        paramView.setCategorie(p.getCategorie());
        paramView.setCodeMedtier(p.getCodeMedtier());
        paramView.setCodeFinance(p.getCodeFinance());
        paramView.setDescription1(p.getDescription1());
        paramView.setAddtionalField(p.getAddtionalField());
        paramView.setAddtionalFieldDescr(p.getAddtionalFieldDescr());
        return paramView;
    }
}
