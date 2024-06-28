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

import ma.inetum.brique.bean.CompteVersementBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.CompteVersementMapper;
import ma.inetum.brique.model.principale.ParamCompteVersement;
import ma.inetum.brique.repository.principale.ParamCompteVersementRepository;
import ma.inetum.brique.services.CompteVersementService;
@Service
public class CompteVersementServiceImpl implements CompteVersementService {

	@Autowired
	private ParamCompteVersementRepository compteVersementRepository;
	CompteVersementMapper mapper = CompteVersementMapper.getInstance();
	private final static Logger logger = LoggerFactory.getLogger(CompteVersementServiceImpl.class);
	@Override
	public List<CompteVersementBean> getAll() {
		var liste = compteVersementRepository.findAll(Sort.by(Direction.ASC, "site"));
		var comptes = liste == null ? new ArrayList<CompteVersementBean>() : 
			liste.stream().map(e -> mapper.transform(e)).toList();
		return comptes;
	}

	@Override
	public void add(CompteVersementBean p) {
		ParamCompteVersement compte = mapper.transform(p);
		compteVersementRepository.save(compte);
	}

	@Override
	public CompteVersementBean findById(Long id) throws ExceptionFonctionnelle {
		if(id == null || id == 0) {
			throw new ExceptionFonctionnelle("Aucun compte choisi, Merci de choisir un compte valide");
		}
		var compte = compteVersementRepository.findById(id);
		if(compte.isEmpty()) {
			throw new ExceptionFonctionnelle("Le comppte choisi n'existe pas, Merci de choisir un compte valide");
		}
		return mapper.transform(compte.get());
	}

	@Override
	public void edit(CompteVersementBean view) throws ExceptionFonctionnelle {
		if(view.getId() == null || view.getId() == 0) {
			throw new ExceptionFonctionnelle("Aucun compte choisi, Merci de choisir un compte valide");
		}
		var _compte = compteVersementRepository.findById(view.getId());
		if(_compte.isEmpty()) {
			throw new ExceptionFonctionnelle("Le comppte choisi n'existe pas, Merci de choisir un compte valide");
		}
		var compte = _compte.get();
		compte.setSite(view.getSite());
		compte.setModePaiement(view.getModePaiement());
		compte.setDescription(view.getDescription());
		compte.setCompteGeneral(view.getCompteGeneral());
		compteVersementRepository.save(compte);
	}

	@Override
	public Map<Map<String, String>, ParamCompteVersement> getAllToMap() {
		Map<Map<String, String>, ParamCompteVersement> map = new HashMap<>();
		List<ParamCompteVersement> comptes = compteVersementRepository.findAll();
		if(comptes != null ) {
			comptes.forEach(compte -> {
				Map<String, String> cle = new HashMap<>();
				if(compte.getSite() != null && !compte.getSite().isBlank() && compte.getModePaiement() != null && !compte.getModePaiement().isBlank()) {
					cle.put(compte.getSite(), compte.getModePaiement());
					map.put(cle, compte);
				} else {
					logger.warn("Code Site '" + compte.getSite() + "' ou le mode de paiement '" + compte.getModePaiement()+ "'+non renseigné au niveau du paramétrage. Ce compte est ignoré");
				}
			});
		}
		return map;
	}

	
}
