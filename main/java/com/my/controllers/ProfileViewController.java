package com.my.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.binding.PreferencesBinding;
import com.my.binding.SearchBinding;
import com.my.model.Member;
import com.my.model.Visit;
import com.my.service.MemberService;
import com.my.service.PhotoService;
import com.my.service.VisitWatcherService;
@Controller
public class ProfileViewController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private VisitWatcherService visitWatcherService;
	
	@Autowired
	private PhotoService photoService;
	
	
	@RequestMapping(value="/profileView",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,
			@RequestParam(value="visUser",required=false)String visUser,HttpSession session){
		model.addAttribute("member",session.getAttribute("member"));
		if(userId!=null || !userId.equals("")){
			Member member = memberService.getMemberById(Long.parseLong(userId));
			List<String> photos = photoService.getPhotoNamesByUserId(Long.parseLong(userId));
			model.addAttribute("member",member);
			model.addAttribute("photos",photos);
			
			if(visUser!=null){
				List<Long> v = visitWatcherService.findVistors(Long.parseLong(userId));			
				Visit visit = visitWatcherService.findVistor(Long.parseLong(visUser));
				if(visit==null){
					visit = new Visit();
					visit.setVisitor(Long.parseLong(visUser));
					visit.setMemId(Long.parseLong(userId));					
				}
				
				visit.setLastVisit(new Date());
				visitWatcherService.save(visit);
			}
			
		}
		
		//model.addAttribute("personalDetails",session.getAttribute("personalDetails"));
		//model.addAttribute("preferences", session.getAttribute("preferences"));
		return "profileView";
		
	}
	
	@RequestMapping(value="/profileView",method=RequestMethod.POST)
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
