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

import com.my.binding.HomeSearch;
import com.my.binding.Login;
import com.my.binding.PersonalDetailsModel;
import com.my.binding.Register;
import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Preferences;
import com.my.service.MemberService;
import com.my.service.PersonalDetailsService;
import com.my.service.PreferencesService;

@Controller
@EnableAutoConfiguration
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PersonalDetailsService personalDetailsService;
	
	@Autowired
	private PreferencesService preferencesService;
	
		
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("login", new Login());
		model.addAttribute("register", new Register());
		model.addAttribute("personalDetails", new PersonalDetailsModel());
		model.addAttribute("homeSearch", new HomeSearch());
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
		model.addAttribute("username", login.getUsername());
		model.addAttribute("password", login.getPassword());		
		String userEmail = login.getUsername();
		String password = login.getPassword();
		if(userEmail!=null){
			Member member = memberService.getMemberByEmail(userEmail);
			if(member!=null){
				httpSession.setAttribute("userId", member.getmemberId());
				PersonalDetails personalDetails = personalDetailsService.getPersonalDetailsByMember(member);
				Preferences preferences = preferencesService.getPreferencesByMember(member);
				if(password.equals(member.getPassword())){
					httpSession.setAttribute("member", member);
					model.addAttribute("member", member);
					model.addAttribute("personalDetails", personalDetails);
					model.addAttribute("preferences", preferences);
					

					
					return "redirect:/myhome?status=success&userId="+member.getmemberId();
					//return "redirect:/profile?status=success&userId="+member.getmemberId();

				}	
			}
		}		
		return "home";		
	}
	
	
	
	

}
