package ma.inetum.brique.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ma.inetum.brique.bean.ProfileEditDto;
import ma.inetum.brique.bean.UserAddDto;
import ma.inetum.brique.bean.UserEditDto;
import ma.inetum.brique.repository.principale.AppUserRepository;
@Component
public class UserValidator implements Validator{

	@Autowired
	AppUserRepository userRepository;
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
	@Override
	public void validate(Object target, Errors errors) {

	}
	
	
	
	public boolean validateUser(UserEditDto userBean, List<String> errors) {
		if(userBean.getUserId() != null && userBean.getUserId() > 0) {
			var user = userRepository.findById(userBean.getUserId());
			if(user.isEmpty()) {
				errors.add("identifiant.erreur");
			} else {
				if(!user.get().getUserName().equals(userBean.getUserName())) {
					errors.add("username.erreur");
				}
				if(userBean.getUserName() == null || userBean.getUserName().isEmpty()) {
					errors.add("username.missing");
				}
			}
		} else {
			if(userBean.getUserName() == null || userBean.getUserName().isEmpty()) {
				errors.add("username.missing");
			} else {
				var  user = userRepository.findUserAccount(userBean.getUserName());
				if(user != null) {
					errors.add("user.already.exist");
				}
			}
		}
		if(userBean.getProfile()== null ||userBean.getProfile()== 0) {
			errors.add("profile.empty");
		}
		return errors.isEmpty() ? true : false;
	}
	public boolean validateUser(UserAddDto userBean, List<String> errors) {
		if(userBean.getUserId() != null && userBean.getUserId() > 0) {
			var user = userRepository.findById(userBean.getUserId());
			if(user.isEmpty()) {
				errors.add("identifiant.erreur");
			} else {
				if(!user.get().getUserName().equals(userBean.getUserName())) {
					errors.add("username.erreur");
				}
				if(userBean.getUserName() == null || userBean.getUserName().isEmpty()) {
					errors.add("username.missing");
				}
			}
		} else {
			if(userBean.getUserName() == null || userBean.getUserName().isEmpty()) {
				errors.add("username.missing");
			} else {
				var  user = userRepository.findUserAccount(userBean.getUserName());
				if(user != null) {
					errors.add("user.already.exist");
				}
			}
		}
		if(userBean.getProfile()== null ||userBean.getProfile()> 0) {
			errors.add("profile.empty");
		}
		return errors.isEmpty() ? true : false;
	}
}
