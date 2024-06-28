package ma.inetum.brique.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseController {

	private List<String> flux = new ArrayList<>();

	public List<String> getFlux() {
		ServletRequestAttributes attr =  (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		@SuppressWarnings("unchecked")
		List<String> userFlux = (List<String>) session.getAttribute("flux");
		return userFlux;
	}
	
}
