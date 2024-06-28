package ma.inetum.brique.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ma.inetum.brique.bean.UserAddDto;
import ma.inetum.brique.bean.UserAllDto;
import ma.inetum.brique.bean.UserEditDto;
import ma.inetum.brique.mapper.UserMapper;
import ma.inetum.brique.model.principale.AppUser;
import ma.inetum.brique.repository.principale.AppProfileRepository;
import ma.inetum.brique.repository.principale.AppRoleRepository;
import ma.inetum.brique.repository.principale.AppUserRepository;
import ma.inetum.brique.repository.principale.ParametrageFluxRepository;
import ma.inetum.brique.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	AppRoleRepository roleRepository;
	@Autowired
	AppProfileRepository profileRepository;
	@Autowired
	ParametrageFluxRepository fluxRepository;
//	@Autowired
//	AppUserRoleRepository userRoleRepository;
	private UserMapper mapper = UserMapper.getInstance();

	@Override
	public List<UserAllDto> findByCriteria(String nom, String prenom, String statut, String role, int page, int size,
			String sort, String dir) {
		Pageable pageable = PageRequest.of(page, size, Direction.fromOptionalString(dir).orElse(Direction.DESC), sort);
		Specification<AppUser> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (nom != null) {
				predicates.add(criteriaBuilder.like(root.get("nom"), nom));
			}
			if (prenom != null) {
				predicates.add(criteriaBuilder.like(root.get("prenom"), prenom));
			}
			if (statut != null) {
				predicates.add(criteriaBuilder.like(root.get("enabled"), statut));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};

		Set<AppUser> users = userRepository.findAll(specification, pageable).toSet();
		return users.stream().map(user -> mapper.userToUserAllDto(user)).collect(Collectors.toList());
	}

	@Override
	public List<UserAllDto> getAll() {
		List<AppUser> users = userRepository.findAll();
		return users.stream().map(user -> mapper.userToUserAllDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserEditDto findById(Long id) {
		if (id == null || id == 0)
			throw new RuntimeException("Identifiant absent");
		AppUser user = userRepository.findById(id).orElse(null);
		UserEditDto c = null;
		if (user != null) {
			c = mapper.userToUserEditDto(user);
		} else {
			throw new RuntimeException("Aucun Client trouvé");
		}
		return c;
	}

	@Override
	public void add(UserAddDto user) {
		AppUser u = userRepository.findUserAccount(user.getUserName());
		if(u != null) {
			throw new RuntimeException("Utilisateur existe déja");
		}
		u = mapper.userAddDtoToUser(user);
		u.setEnabled(true);
		if(user.getProfile() != null && user.getProfile()> 0)
			u.setProfile(profileRepository.getOne(user.getProfile()));
		if(user.getFlux() != null && user.getFlux().length > 0) {
			for(String f : user.getFlux()) {
				u.getFlux().add(fluxRepository.findByCode(f));
			}
		}
		u = userRepository.save(u);
//		AppUserRole userRole = new AppUserRole();
//		List<AppRole> roles = roleRepository.findAll().stream().filter(e -> e.getRoleName().equalsIgnoreCase(user.getRole())).toList();
//		userRole.setAppRole(roles.stream().findFirst().orElse(null));
//		userRole.setAppUser(u);
//		userRole = userRoleRepository.save(userRole);
	}

	@Override
	public void edit(UserEditDto c) {
		if (c.getUserId() == null || c.getUserId() == 0) {
			throw new RuntimeException("Identifiant absent");
		}
		AppUser user = userRepository.findById(c.getUserId()).orElse(null);
		if (user != null) {
			user.setEnabled(c.getEnabled());
			user.setNom(c.getNom());
			user.setPrenom(c.getPrenom());
			user.setUserName(c.getUserName());
			user.setEnabled(true);
			if(c.getProfile() != null && c.getProfile()> 0)
				user.setProfile(profileRepository.getOne(c.getProfile()));
			user.setFlux(new HashSet<>());
			if(c.getFlux() != null && c.getFlux().length > 0) {
				for(String f : c.getFlux()) {
					user.getFlux().add(fluxRepository.findByCode(f));
				}
			}
			user = userRepository.save(user);
//			AppUserRole userRole = new AppUserRole();
//			List<AppRole> roles = roleRepository.findAll().stream().filter(e -> e.getRoleName().equalsIgnoreCase(c.getRole())).toList();
//			List<AppUserRole> userRoles =userRoleRepository.findByUserId(c.getUserId()) ;
//			userRoleRepository.deleteAll(userRoles);
//			userRole.setProfile(roles.stream().findFirst().orElse(null));
//			userRole.setAppUser(user);
//			userRole = userRoleRepository.save(userRole);
		} else {
			throw new RuntimeException("Aucun Client trouvé");
		}
		
	}

//	@Override
//	public UserDetails findByUserName(String username) {
//		AppUser user = userRepository.findUserAccount(username);
////		if(user == null)
////			throw Usernotf
//		return null;
//	}

	
}
