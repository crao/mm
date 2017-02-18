package com.my.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.binding.HomeSearch;
import com.my.model.Member;
import com.my.service.SearchService;

@Controller
public class BasicSearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value="/homeSearch", method=RequestMethod.GET)
	public String search(){
		return "home";
	}
	@RequestMapping(value = "/homeSearch", method = RequestMethod.POST)
	public String basicSearch(@ModelAttribute HomeSearch homeSearch,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession) {
		
		List<Member> result = searchService.basicSearch(homeSearch);
		
		model.addAttribute("members",result);
		
		return "homeSearch";
	}
}
