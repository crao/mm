package com.my.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MyProfileViewController {
	@RequestMapping(value="/myProfileView",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
				
		return "/myProfileView";
		
	}
	
	@RequestMapping(value="/myProfileView",method=RequestMethod.POST)
	public String show(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
				
		return "redirect:/profile?status=success?userId="+userId;
		
	}
}
