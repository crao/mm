package com.my.controllers;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.my.binding.Login;

import com.my.binding.Register;
import com.my.model.Member;

import com.my.service.MemberService;


@Controller
@EnableAutoConfiguration
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	
		
	@RequestMapping(value="/", method=RequestMethod.GET)
	
	public String home(Model model) {
		model.addAttribute("login", new Login());
		model.addAttribute("register", new Register());
		model.addAttribute("vendor", new Register());
	
		return "home";
	}
 
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String login(@ModelAttribute Login login,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession) {
		if (bindingResult.hasErrors()) {
			return "home";
		}
	
		String userEmail = login.getEmail();
		String password = login.getPassword();
		if(userEmail!=null){
			Member member = memberService.getMemberByEmail(userEmail);
	
			if(password.equals(member.getPassword())){
				
				model.addAttribute("member", member);
				
			
				return "profile";
			}				
		}		
		return "home";		
	}
	
	
	
	

}
