package ma.inetum.brique.services;

import ma.inetum.brique.bean.ParamSupplierView;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ParametrageSupplierService {

    public List<ParamSupplierView> getAll();
    public DataTablesOutput<ParamSupplierView> getAll(DataTablesInput input);

    public void add(ParamSupplierView p);
    public ParamSupplierView findById(Long id);

    public void edit(ParamSupplierView view);
}
