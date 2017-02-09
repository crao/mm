package com.my.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.binding.PreferencesBinding;
import com.my.binding.SearchBinding;

@Controller
public class DailyMatchesController {
	
	@RequestMapping(value="/dailymatches",method=RequestMethod.GET)
	public String start(Model model){
		return "dailymatches";
	}
	
	
	@RequestMapping(value="/dailymatches",method=RequestMethod.POST)
	public String show(@ModelAttribute PreferencesBinding preferencesBinding,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession){
		
		model.addAttribute("search", new SearchBinding());
		return "dailymatches";
	}

}
