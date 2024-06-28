package ma.inetum.brique.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.inetum.brique.model.principale.Erreur;
import ma.inetum.brique.repository.principale.ErreurRepository;
import ma.inetum.brique.services.ErreurService;
@Service
public class ErreurServiceImpl implements ErreurService {

	@Autowired
	ErreurRepository erreurRepository;
	@Override
	public Erreur saveErreur(Erreur erreur) {
		return erreurRepository.save(erreur);
	}

	
}
