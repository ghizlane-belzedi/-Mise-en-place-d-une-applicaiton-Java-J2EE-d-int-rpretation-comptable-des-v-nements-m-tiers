package ma.inetum.brique.services;

import ma.inetum.brique.bean.ParamView;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ParametrageService {
    public List<ParamView> findallByCategorie(String categorie);
    public DataTablesOutput<ParamView> findallByCategorie(DataTablesInput input, String categorie);
    public List<String> getCategories();
    public List<ParamView> getAll();
    public DataTablesOutput<ParamView> getAll(DataTablesInput input);
    public void add(ParamView p, String categorie);
    public ParamView findById(Long id);

    public void edit(ParamView view);

}
