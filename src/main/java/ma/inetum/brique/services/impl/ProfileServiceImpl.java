package ma.inetum.brique.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.inetum.brique.bean.ProfileAllDto;
import ma.inetum.brique.bean.ProfileEditDto;
import ma.inetum.brique.mapper.ProfileMapper;
import ma.inetum.brique.model.principale.AppProfile;
import ma.inetum.brique.model.principale.AppRole;
import ma.inetum.brique.repository.principale.AppProfileRepository;
import ma.inetum.brique.repository.principale.AppRoleRepository;
import ma.inetum.brique.services.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private AppProfileRepository profileRepository;
	@Autowired
	AppRoleRepository roleRepository;
	private ProfileMapper mapper = ProfileMapper.getInstance();

	@Override
	public List<ProfileAllDto> findByCriteria(String code, String descrption, String role, int page, int size, String sort, String dir) {
		Set<AppProfile> profiles = profileRepository.searchByCriteria(code, descrption, role, page, size, sort, dir);
		return profiles.stream().map(profile -> mapper.profileToProfileAllDto(profile)).
				collect(Collectors.toList());
	}

	@Override
	public List<ProfileAllDto> getAll() {
		List<AppProfile> profiles = profileRepository.findAll();
		return profiles.stream().map(profile -> mapper.profileToProfileAllDto(profile)).collect(Collectors.toList());
	}

	@Override
	public ProfileEditDto findById(Long id) {
		if (id == null || id == 0)
			throw new RuntimeException("Identifiant absent");
		AppProfile profile = profileRepository.findById(id).orElse(null);
		ProfileEditDto c = null;
		if (profile != null) {
			c = mapper.profileToProfileEditDto(profile);
		} else {
			throw new RuntimeException("Aucun Client trouvé");
		}
		return c;
	}

	@Override
	public void add(ProfileEditDto c) {
		List<AppProfile> list = profileRepository.findByCode(c.getCode());
		if(list != null && list.size() > 0) {
			throw new RuntimeException("Profile existe déja");
		}
		AppProfile profile = mapper.profileDtoToProfile(c);
		profile.setRoles(c.getRoles().stream().map(e -> roleRepository.findByCode(e)).collect(Collectors.toList()));
		profile = profileRepository.save(profile);
	}

	@Override
	public void edit(ProfileEditDto c) {
		if (c.getId() == null || c.getId() == 0) {
			throw new RuntimeException("Identifiant absent");
		}
		AppProfile profile = profileRepository.findById(c.getId()).orElse(null);
		if (profile != null) {
			profile.setNom(c.getDescription());
			List<AppRole> roles = c.getRoles().stream().map(e -> roleRepository.findByCode(e)).collect(Collectors.toList());
			profile.setRoles(new ArrayList<>(roles));
			profile = profileRepository.save(profile);
		} else {
			throw new RuntimeException("Aucun Client trouvé");
		}

	}
	@Override
	public boolean deleteById(Long id) {
		var profil = profileRepository.findById(id);
		if(profil.isPresent() &&( profil.get().getUsers() == null || profil.get().getUsers().isEmpty())) {
			profileRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
