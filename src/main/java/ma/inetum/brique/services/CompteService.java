package ma.inetum.brique.services;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import ma.inetum.brique.bean.CompteView;
import ma.inetum.brique.bean.SchemaEditView;

public interface CompteService {

	public DataTablesOutput<CompteView> getAll(DataTablesInput input);
	public void add(SchemaEditView s);
	public void edit(SchemaEditView s);
	public SchemaEditView findById(Long id);
}
