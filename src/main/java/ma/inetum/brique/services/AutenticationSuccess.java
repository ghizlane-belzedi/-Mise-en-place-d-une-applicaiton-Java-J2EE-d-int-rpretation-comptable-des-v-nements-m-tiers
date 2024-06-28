package ma.inetum.brique.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ma.inetum.brique.model.principale.AppUser;
import ma.inetum.brique.repository.principale.AppUserRepository;
@Component
public class AutenticationSuccess extends SavedRequestAwareAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(AutenticationSuccess.class);
	
	private class UserBean {
		private String userName;
		private String fullName;

		@Override
		public String toString() {
			return this.fullName;
		}
//		public String getUserName() {
//			return userName;
//		}
//		public void setUserName(String userName) {
//			this.userName = userName;
//		}
//		public String getFullName() {
//			return fullName;
//		}
//		public void setFullName(String fullName) {
//			this.fullName = fullName;
//		}

		public UserBean(String userName, String fullName) {
			this.userName = userName;
			this.fullName = fullName;
		}
	}

	@Autowired
	AppUserRepository userRepository;

//	@Value("${app.version}")
//	String version;
//	@Value("${app.time.build}")
//	String timeBuild;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
//		super.onAuthenticationSuccess(request, response, authentication);
		logger.info("Login from session: " + request.getParameter("username"));
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null) {
			AppUser user_ = userRepository.findUserAccount(user.getUsername());
			request.getSession().setAttribute("currentUser", new UserBean(user_.getUserName(), String.join(" ", user_.getNom(), user_.getPrenom())));
			List<String> fluxList = user_.getFlux().stream().map(e->e.getCode()).collect(Collectors.toList());
			request.getSession().setAttribute("flux", fluxList);
			logger.info(new UserBean(user_.getUserName(), String.join(" ", user_.getNom(), user_.getPrenom())).fullName);
//			request.getSession().setAttribute("version", version);
		} else {
			request.getSession().setAttribute("currentUser", null);
		}
		String ctxtName = request.getServletContext().getContextPath();
		response.sendRedirect(ctxtName + "/home");
	}


}
