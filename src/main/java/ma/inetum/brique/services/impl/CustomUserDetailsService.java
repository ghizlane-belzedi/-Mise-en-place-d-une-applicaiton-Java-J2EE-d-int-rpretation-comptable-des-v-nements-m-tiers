package ma.inetum.brique.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ma.inetum.brique.bean.CustomUserDetails;
import ma.inetum.brique.model.principale.AppUser;
import ma.inetum.brique.repository.principale.AppUserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepo.findUserAccount(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
}
