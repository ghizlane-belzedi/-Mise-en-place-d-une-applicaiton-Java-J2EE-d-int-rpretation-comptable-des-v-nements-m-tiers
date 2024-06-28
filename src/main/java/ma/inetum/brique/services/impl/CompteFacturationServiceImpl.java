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

import ma.inetum.brique.bean.CompteFacturationBean;
import ma.inetum.brique.exception.ExceptionFonctionnelle;
import ma.inetum.brique.mapper.CompteFacturationMapper;
import ma.inetum.brique.model.principale.ParamCompteFacturation;
import ma.inetum.brique.repository.principale.ParamCompteFacturationRepository;
import ma.inetum.brique.services.CompteFacturationService;
@Service
public class CompteFacturationServiceImpl implements CompteFacturationService {

	@Autowired
	private ParamCompteFacturationRepository compteFacturationRepository;
	CompteFacturationMapper mapper = CompteFacturationMapper.getInstance();
	private final static Logger logger = LoggerFactory.getLogger(CompteFacturationServiceImpl.class);
	@Override
	public List<CompteFacturationBean> getAll() {
		var liste = compteFacturationRepository.findAll(Sort.by(Direction.ASC, "site"));
		var comptes = liste == null ? new ArrayList<CompteFacturationBean>() : 
			liste.stream().map(e -> mapper.transform(e)).toList();
		return comptes;
	}

	@Override
	public void add(CompteFacturationBean p) {
		ParamCompteFacturation compte = mapper.transform(p);
		compteFacturationRepository.save(compte);
	}

	@Override
	public CompteFacturationBean findById(Long id) throws ExceptionFonctionnelle {
		if(id == null || id == 0) {
			throw new ExceptionFonctionnelle("Aucun compte choisi, Merci de choisir un compte valide");
		}
		var compte = compteFacturationRepository.findById(id);
		if(compte.isEmpty()) {
			throw new ExceptionFonctionnelle("Le comppte choisi n'existe pas, Merci de choisir un compte valide");
		}
		return mapper.transform(compte.get());
	}

	@Override
	public void edit(CompteFacturationBean view) throws ExceptionFonctionnelle {
		if(view.getId() == null || view.getId() == 0) {
			throw new ExceptionFonctionnelle("Aucun compte choisi, Merci de choisir un compte valide");
		}
		var _compte = compteFacturationRepository.findById(view.getId());
		if(_compte.isEmpty()) {
			throw new ExceptionFonctionnelle("Le comppte choisi n'existe pas, Merci de choisir un compte valide");
		}
		var compte = _compte.get();
		compte.setSite(view.getSite());
		compte.setTva(view.getTva());
		compte.setDesc1(view.getDesc1());
		compte.setDesc2(view.getDesc2());
		compte.setDesc3(view.getDesc3());
		compte.setCompteGeneral(view.getCompteGeneral());
		compte.setCompteClass3(view.getCompteClass3());
		compte.setCompteClass4(view.getCompteClass4());
		compteFacturationRepository.save(compte);
	}

	@Override
	public Map<Map<String, Boolean>, ParamCompteFacturation> getAllToMap() {
		Map<Map<String, Boolean>, ParamCompteFacturation> map = new HashMap<>();
		List<ParamCompteFacturation> comptes = compteFacturationRepository.findAll();
		if(comptes != null ) {
			comptes.forEach(compte -> {
				Map<String, Boolean> cle = new HashMap<>();
				if(compte.getSite() != null && !compte.getSite().isBlank()) {
					cle.put(compte.getSite(), compte.getTva() != null ? compte.getTva() : Boolean.FALSE);
					map.put(cle, compte);
				} else {
					logger.warn("Code Site '" + compte.getSite() + "' non renseigné au niveau du paramétrage. Ce compte est ignoré");
				}
			});
		}
		return map;
	}

	
}
