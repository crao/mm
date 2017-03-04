package com.my.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.binding.PreferencesBinding;
import com.my.binding.SearchBinding;

@Controller
public class ProfileController {
	
	@RequestMapping(value="/profile",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
		model.addAttribute("member",session.getAttribute("member"));
		//model.addAttribute("personalDetails",session.getAttribute("personalDetails"));
		//model.addAttribute("preferences", session.getAttribute("preferences"));
		return "profile";
	}
	
	
	@RequestMapping(value="/profile",method=RequestMethod.POST)
	public String show(@ModelAttribute PreferencesBinding preferencesBinding,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession,
			@RequestParam(value="status",required=false)String status){
		Long userId = (Long)httpSession.getAttribute("userId");
		model.addAttribute("search", new SearchBinding());
		return "redirect:/profile?status=success?userId="+userId;
	}

}
