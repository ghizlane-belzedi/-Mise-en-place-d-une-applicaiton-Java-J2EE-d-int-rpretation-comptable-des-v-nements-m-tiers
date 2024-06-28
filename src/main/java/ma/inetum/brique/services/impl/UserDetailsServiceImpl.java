package ma.inetum.brique.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.inetum.brique.model.principale.AppRole;
import ma.inetum.brique.model.principale.AppUser;
import ma.inetum.brique.repository.principale.AppRoleRepository;
import ma.inetum.brique.repository.principale.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepositoryre;

	@Autowired
	private AppRoleRepository appRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser appUser = this.appUserRepositoryre.findUserAccount(userName);

		if (appUser == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		System.out.println("Found User: " + appUser);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = new ArrayList<>();
		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
		if(appUser.getProfile() != null && appUser.getProfile().getRoles() != null) {
			for(AppRole role : appUser.getProfile().getRoles()) {
				grantList.add(new SimpleGrantedAuthority(role.getRoleName()));
				grantList.add(new SimpleGrantedAuthority(role.getModule()));
				grantList.add(new SimpleGrantedAuthority(role.getController()));
			}
		}
		
//		if (roleNames != null) {
//			for (String role : roleNames) {
//				// ROLE_USER, ROLE_ADMIN,..
//				GrantedAuthority authority = new SimpleGrantedAuthority(role);
//				grantList.add(authority);
//			}
//		}

		UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
				appUser.getEncrytedPassword(), grantList);

		return userDetails;
	}

}