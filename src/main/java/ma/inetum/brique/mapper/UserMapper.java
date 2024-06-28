package ma.inetum.brique.mapper;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import ma.inetum.brique.bean.UserAddDto;
import ma.inetum.brique.bean.UserAllDto;
import ma.inetum.brique.bean.UserEditDto;
import ma.inetum.brique.model.principale.AppUser;

public class UserMapper {

	private static UserMapper mapper;

	private UserMapper() {
	}

	public static UserMapper getInstance() {

		// create object if it's not already created
		if (mapper == null) {
			mapper = new UserMapper();
		}

		// returns the singleton object
		return mapper;
	}

	public UserAllDto userToUserAllDto(AppUser c) {
		UserAllDto user = new UserAllDto();
		user.setEnabled(c.getEnabled());
		user.setNom(c.getNom());
		user.setPrenom(c.getPrenom());
		user.setUserName(c.getUserName());
		user.setProfile(c.getProfile() != null ? c.getProfile().getNom() : "");
		user.setUserId(c.getUserId());
		return user;
	}
	public AppUser userAddDtoToUser(UserAddDto c) {
		AppUser user = new AppUser();
		user.setEnabled(c.getEnabled());
		user.setNom(c.getNom());
		user.setPrenom(c.getPrenom());
		user.setUserName(c.getUserName());
		byte[] bytesEncoded = Base64.getEncoder().encode(c.getPassword().getBytes());
		String passwordEncoded = new String(bytesEncoded);
		user.setEncrytedPassword(passwordEncoded);
		return user;
	}
	public UserEditDto userToUserEditDto(AppUser c) {
		UserEditDto user = new UserEditDto();
		user.setEnabled(c.getEnabled());
		user.setNom(c.getNom());
		user.setPrenom(c.getPrenom());
		user.setUserName(c.getUserName());
		user.setUserId(c.getUserId());
		user.setProfile(c.getProfile() != null ? c.getProfile().getId(): null);
		user.setFlux(c.getFlux().stream().map(e-> e.getCode()).collect(Collectors.toList()).toArray(String[]::new));
		return user;
	}

}
