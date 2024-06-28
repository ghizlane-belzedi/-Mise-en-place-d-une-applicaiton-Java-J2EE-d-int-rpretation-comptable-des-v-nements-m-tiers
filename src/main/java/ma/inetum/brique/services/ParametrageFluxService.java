package ma.inetum.brique.services;

import java.util.List;

import ma.inetum.brique.bean.FluxEditView;
import ma.inetum.brique.bean.FluxView;
import ma.inetum.brique.model.principale.ParametrageFLux;

public interface ParametrageFluxService {

	public List<FluxView> findByCriteria(String nom, String code, int page, int size, String sort, String dir);
	public List<FluxView> getAll();
	public FluxEditView findById(Long id) ;
	public void edit(FluxEditView contrat) ;
	public ParametrageFLux findFluxByCode (String fluxCode) ;
}
