package ma.inetum.brique.services;

import java.util.List;
import java.util.Map;

import ma.inetum.brique.bean.ClientBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.model.principale.ParametrageClient;

public interface ClientService {

	public List<ClientBean> getAll();
    public void add(ClientBean p);
    public ClientBean findById(Long id) throws ExceptionFonctionnelle;
    public void edit(ClientBean view) throws ExceptionFonctionnelle;
    public Map<Map<String, String>, ParametrageClient> getAllToMap();
	public ParametrageClient getDefaultCustomer();
}
