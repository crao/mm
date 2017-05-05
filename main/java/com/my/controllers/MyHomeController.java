package com.my.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.model.Member;
import com.my.model.Photo;
import com.my.model.Visit;
import com.my.service.MemberService;
import com.my.service.PhotoService;
import com.my.service.VisitWatcherService;


import com.my.service.PhotoService;

@Controller
public class MyHomeController {



	@Autowired 	
	VisitWatcherService visitWatcherService; 
	
	@Autowired
	MemberService memberService;
	

	@Autowired
	private PhotoService photoService;
	
	@RequestMapping(value="/myhome",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
			Member member = (Member)session.getAttribute("member");
			model.addAttribute("member", member);
			
			Photo profilePhoto = photoService.getProfilePhoto(Long.parseLong(userId));

			if(profilePhoto!=null){
				model.addAttribute("profilePhoto", profilePhoto.getFileName());
			}else{
				model.addAttribute("profilePhoto", null);
			}
			
			List<Long> visitorIds = new ArrayList<Long>();
			
			Map<Long,Date> visitMap = new HashMap<Long, Date>();
			
			List<Visit> visits = visitWatcherService.findVisits(Long.parseLong(userId));
			
			for(Visit v:visits){
				visitMap.put(v.getMemId(), v.getLastVisit());
				visitorIds.add(v.getVisitor());
			}
			
			List<Member> visitors = memberService.getByMemberIds(visitorIds);
			model.addAttribute("vistors", visitors);
			model.addAttribute("visitMap",visitMap);

		return "myhome";
		
	}
	
	@RequestMapping(value="/myhome",method=RequestMethod.POST)
	public String show(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
				
		return "myhome";
		
	}
}
