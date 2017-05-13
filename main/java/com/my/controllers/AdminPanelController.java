package com.my.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.binding.HomeSearch;
import com.my.binding.Login;
import com.my.binding.PersonalDetailsModel;
import com.my.binding.Register;
import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Preferences;
import com.my.service.MemberService;
import com.my.service.MessageService;
import com.my.service.VisitWatcherService;

@Controller
public class AdminPanelController {

	
	@Autowired
	MemberService memberService;
	
	@Autowired 	
	VisitWatcherService visitWatcherService; 
	
	
	
	
	@RequestMapping(value="/adminlogin",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
		HttpSession session){

		
		model.addAttribute("adminlogin", new Login());
		
	
	
		  return "adminlogin";
	
	}
	

	
	@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
	
	public String show(@ModelAttribute Login login,Model model,@RequestParam(value="status",required=false)String status,
			HttpSession session){
				
		model.addAttribute("username", login.getUsername());
		model.addAttribute("password", login.getPassword());		
		String userEmail = login.getUsername();
		String password = login.getPassword();
		
		Member member = memberService.getMemberByEmail(userEmail);		
		List<Long> visitorIds = memberService.findMember();
		List<Member> visitors = memberService.getByMemberIds(visitorIds);
		session.setAttribute("userId", member.getmemberId());
		if(userEmail!=null && password.equals(member.getPassword()))
		{			     	
			     	model.addAttribute("vistors", visitors);
			    	return "admindashboard";	
		}
				
		
		  return "Enter Correct Information";
		
	}

}
