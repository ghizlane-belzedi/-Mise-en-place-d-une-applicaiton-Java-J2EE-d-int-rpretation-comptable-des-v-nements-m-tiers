package ma.inetum.brique.mapper;

import ma.inetum.brique.bean.ParamSupplierView;
import ma.inetum.brique.model.principale.ParametrageSupplier;

public class ParamSupplierMapper {

    private static ParamSupplierMapper mapper;

    private ParamSupplierMapper() {
    }

    public static ParamSupplierMapper getInstance() {

        // create object if it's not already created
        if (mapper == null) {
            mapper = new ParamSupplierMapper();
        }

        // returns the singleton object
        return mapper;
    }

    public ParamSupplierView paramSupplierToView(ParametrageSupplier p){
        ParamSupplierView paramSupplierView = new ParamSupplierView();
        paramSupplierView.setId(p.getId());
        paramSupplierView.setCodeMedtier(p.getCodeMedtier());
        paramSupplierView.setCodeFinance(p.getCodeFinance());
        paramSupplierView.setDescription1(p.getDescription1());
        paramSupplierView.setType(p.getType());
        paramSupplierView.setCurrency(p.getCurrency());

        return paramSupplierView;
    }
}
