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
import com.my.service.VisitWatcherService;
@Controller
public class MyProfileViewController {
	@Autowired 	
	VisitWatcherService visitWatcherService; 

	@Autowired
	MemberService memberService;

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

		List<Long> visitorIds = visitWatcherService.findVistors(Long.parseLong(userId));
		List<Member> visitors = memberService.getByMemberIds(visitorIds);
		
		
		model.addAttribute("vistors", visitors);
		return "myProfileView";
		
	}
	
}
