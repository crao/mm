package com.my.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.model.Member;
import com.my.service.MemberService;
import com.my.service.MessageService;
import com.my.service.VisitWatcherService;

@Controller
public class EditPageController {
	
	@Autowired
	MemberService memberService;
	@Autowired 	
	MessageService messageService; 

	@RequestMapping(value="/editpage",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,
			@RequestParam(value="message",required=false)String visId,HttpSession session){		
		
		model.addAttribute("member",session.getAttribute("member"));
		if(userId!=null || !userId.equals("")){
			Member member = memberService.getMemberById(Long.parseLong(userId));
			model.addAttribute("member",member);
			
			
			
		}
		
		  
		  return "editpage";
		
	}
	
	@RequestMapping(value="/editpage",method=RequestMethod.POST)
	public String show(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,
			@RequestParam(value="message",required=false)String message,HttpSession session){
				
		return "redirect:/profile?status=success?userId="+userId;
		
		
	}

}
