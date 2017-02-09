package com.my.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.binding.HomeSearch;
import com.my.binding.Login;

@Controller

public class BasicSearchController {
	
	@RequestMapping(value="/homeSearch", method=RequestMethod.GET)
	public String search(){
		return "home";
	}
	@RequestMapping(value = "/homeSearch", method = RequestMethod.POST)
	public String basicSearch(@ModelAttribute HomeSearch homeSearch,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession) {
		
		return "homeSearch";
	}
}
