package ma.inetum.brique.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ma.inetum.brique.bean.ClientBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.ClientMapper;
import ma.inetum.brique.model.principale.ParametrageClient;
import ma.inetum.brique.repository.principale.ParametrageClientRepository;
import ma.inetum.brique.services.ClientService;
import ma.inetum.brique.utilities.Constantes;
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ParametrageClientRepository clientRepository;
	ClientMapper mapper = ClientMapper.getInstance();
	private final static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	@Override
	public List<ClientBean> getAll() {
		var liste = clientRepository.findAll(Sort.by(Direction.ASC, "site"));
		var clients = liste == null ? new ArrayList<ClientBean>() : 
			liste.stream().map(e -> mapper.transform(e)).toList();
		return clients;
	}

	@Override
	public void add(ClientBean p) {
		ParametrageClient compte = mapper.transform(p);
		clientRepository.save(compte);
	}

	@Override
	public ClientBean findById(Long id) throws ExceptionFonctionnelle {
		if(id == null || id == 0) {
			throw new ExceptionFonctionnelle("Aucun compte choisi, Merci de choisir un compte valide");
		}
		var client = clientRepository.findById(id);
		if(client.isEmpty()) {
			throw new ExceptionFonctionnelle("Le comppte choisi n'existe pas, Merci de choisir un compte valide");
		}
		return mapper.transform(client.get());
	}

	@Override
	public void edit(ClientBean view) throws ExceptionFonctionnelle {
		if(view.getId() == null || view.getId() == 0) {
			throw new ExceptionFonctionnelle("Aucun compte choisi, Merci de choisir un compte valide");
		}
		var _client = clientRepository.findById(view.getId());
		if(_client.isEmpty()) {
			throw new ExceptionFonctionnelle("Le comppte choisi n'existe pas, Merci de choisir un compte valide");
		}
		var client = _client.get();
		client.setSite(view.getSite());
		client.setCodeFinance(view.getCodeFinance());
		client.setCodeMedtier(view.getCodeMedtier());
		client.setDescription(view.getDescription());
		clientRepository.save(client);
	}

	@Override
	public Map<Map<String, String>, ParametrageClient> getAllToMap() {
		Map<Map<String, String>, ParametrageClient> map = new HashMap<>();
		List<ParametrageClient> clients = clientRepository.findAll();
		if(clients != null ) {
			clients.forEach(client -> {
				Map<String, String> cle = new HashMap<>();
				if(client.getSite() != null && !client.getSite().isBlank() && client.getCodeMedtier() != null && !client.getCodeMedtier().isBlank()) {
					cle.put(client.getSite(), client.getCodeMedtier());
					map.put(cle, client);
				} else {
					logger.warn("Code Site '" + client.getSite() + "' ou Code Metier '" + client.getCodeMedtier() + "' non renseigné au niveau du paramétrage. Ce compte est ignoré");
				}
			});
		}
		return map;
	}
	
	@Override
	public ParametrageClient getDefaultCustomer() {
		ParametrageClient result = null;
		List<ParametrageClient> clients = clientRepository.findByCodeMetierAndSite(Constantes.DEFAULT_CUSTOMER_CODE, Constantes.DEFAULT_SITE);
		if ((clients != null) && (!clients.isEmpty())) {
			result = clients.get(0);
		}
		return result;
	}
}
