package org.id.sec;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {
	
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.PUT)
	public String logoutDo(Model model, HttpServletRequest request,HttpServletResponse response){
	HttpSession session= request.getSession(false);
	    SecurityContextHolder.clearContext();
	         session= request.getSession(false);
	        if(session != null) {
	            session.invalidate();
	        }
	        for(Cookie cookie : request.getCookies()) {
	            cookie.setMaxAge(0);
	        }
			//model.addAttribute("logout", true);
			//System.out.println("////////////////// ");

	    return "login";
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String home(Model model, Principal user) {
		 if (user == null) {
			 return "login";
		 }
		 else {
		return"redirect:/checkAccount";}
	}
	@RequestMapping(value="/403")
	public String accessDenied() {
		return"403";
	}

}
