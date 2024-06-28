package ma.inetum.brique.mapper;

import ma.inetum.brique.bean.ProfileAllDto;
import ma.inetum.brique.bean.ProfileEditDto;
import ma.inetum.brique.model.principale.AppProfile;

import java.util.stream.Collectors;

public class ProfileMapper {

	private static ProfileMapper mapper;

	private ProfileMapper() {
	}

	public static ProfileMapper getInstance() {

		// create object if it's not already created
		if (mapper == null) {
			mapper = new ProfileMapper();
		}

		// returns the singleton object
		return mapper;
	}

	public ProfileAllDto profileToProfileAllDto(AppProfile c) {
		ProfileAllDto profile = new ProfileAllDto();
		profile.setId(c.getId());
		profile.setCode(c.getCode());
		profile.setDescription(c.getNom());
		profile.setNombreUsers(c.getUsers().size());
		profile.setEnabled(c.getEnabled());
		return profile;
	}
	public AppProfile profileDtoToProfile(ProfileEditDto c) {
		AppProfile profile = new AppProfile();
		profile.setCode(c.getCode());
		profile.setNom(c.getDescription());
		profile.setEnabled(true);
		return profile;
	}
	public ProfileEditDto profileToProfileEditDto(AppProfile c) {
		ProfileEditDto profile = new ProfileEditDto();
		profile.setId(c.getId());
		profile.setCode(c.getCode());
		profile.setDescription(c.getNom());
		profile.setRoles(c.getRoles().stream().map(e -> e.getRoleName()).collect(Collectors.toList()));
		return profile;
	}

}
