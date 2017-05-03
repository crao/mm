package com.my.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.model.Member;
import com.my.model.Photo;
import com.my.service.PhotoService;
@Controller
public class MyHomeController {
	
	@Autowired
	private PhotoService photoService;
	
	@RequestMapping(value="/myhome",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
			Member member = (Member)session.getAttribute("member");
			model.addAttribute("member", member);
			
			Photo profilePhoto = photoService.getProfilePhoto(Long.parseLong(userId));
			model.addAttribute("profilePhoto", profilePhoto.getFileName());
				
				
		return "myhome";
		
	}
	
	@RequestMapping(value="/myhome",method=RequestMethod.POST)
	public String show(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
				
		return "myhome";
		
	}
}
