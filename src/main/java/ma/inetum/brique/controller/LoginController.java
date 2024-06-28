package ma.inetum.brique.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)    
    public String login(@RequestParam(value = "error", defaultValue = "false") boolean loginError, Model model) {    
            model.addAttribute("error", loginError);
        return "login";    
    }
	@RequestMapping(value="/logout", method=RequestMethod.GET)    
    public String logout() {    
            
        return "login";    
    }
	@RequestMapping(value={"/home", "/"}, method=RequestMethod.GET)    
    public String home() {    
        return "home";    
    }    
}
