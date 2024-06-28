package ma.inetum.brique.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ma.inetum.brique.bean.ProfileEditDto;
import ma.inetum.brique.repository.principale.AppProfileRepository;

@Component
public class ProfilValidator implements Validator{
	@Autowired
	AppProfileRepository profilRepository;

	

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
	
	@Override
	public void validate(Object target, Errors errors) {

	}

	public boolean validateProfil(ProfileEditDto profilBean, List<String> errors) {
		if(profilBean.getId() != null && profilBean.getId() > 0) {
			var profile = profilRepository.findById(profilBean.getId());
			if(profile.isEmpty()) {
				errors.add("identifiant.erreur");
			} else {
				if(!profile.get().getCode().equals(profilBean.getCode())) {
					errors.add("profil.code.erreur");
				}
				if(profilBean.getDescription() == null || profilBean.getDescription().isEmpty()) {
					errors.add("nom.missing");
				}
			}
		} else {
			if(profilBean.getCode() == null || profilBean.getCode().isEmpty()) {
				errors.add("code.missing");
			} else {
				var  profil = profilRepository.findByCode(profilBean.getCode());
				if(profil != null && !profil.isEmpty()) {
					errors.add("profil.already.exist");
				}
			}
			if(profilBean.getDescription() == null || profilBean.getDescription().isEmpty()) {
				errors.add("nom.missing");
			}
		}
		if(profilBean.getRoles()== null || profilBean.getRoles().isEmpty()) {
			errors.add("list.previlege.empty");
		}
		return errors.isEmpty() ? true : false;
	}

	public boolean validateProfil(Long id, String code, String nom, List<String> roles, List<String> errors) {
		if(id != null && id > 0) {
			var profile = profilRepository.findById(id);
			if(profile.isEmpty()) {
				errors.add("identifiant.erreur");
			} else {
				if(!profile.get().getCode().equals(code)) {
					errors.add("profil.code.erreur");
				}
				if(nom == null || nom.isEmpty()) {
					errors.add("nom.missing");
				}
			}
		} else {
			if(code == null || code.isEmpty()) {
				errors.add("code.missing");
			} else {
				var  profil = profilRepository.findByCode(code);
				if(profil != null && !profil.isEmpty()) {
					errors.add("profil.already.exist");
				}
			}
			if(nom == null || nom.isEmpty()) {
				errors.add("nom.missing");
			}
		}
		if(roles == null || roles.isEmpty()) {
			errors.add("list.previlege.empty");
		}
		return errors.isEmpty() ? true : false;
	}

}
