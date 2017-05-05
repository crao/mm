
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
import org.springframework.web.bind.annotation.RequestParam;

import com.my.binding.LifeStyleBinding;
import com.my.binding.PreferencesBinding;
import com.my.dao.MatchDao;
import com.my.model.Lifestyle;
import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Preferences;
import com.my.service.LifeStyleService;
import com.my.service.MemberService;
import com.my.service.PersonalDetailsService;
import com.my.service.PreferencesService;

@Controller
public class LifeStyleController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PreferencesService preferencesService;
	
	@Autowired
	PersonalDetailsService personalDetailsService;
	
	@Autowired
	LifeStyleService lifestyleService; 
	
	@Autowired
	MatchDao matchDao;
	
	
	
	@RequestMapping(value="/lifestyle",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,@RequestParam(value="userId",required=false)String userId,HttpSession session){
		if(status==null)
			return "lifestyle";
		else{
			model.addAttribute("member",session.getAttribute("member"));
			model.addAttribute("personalDetails",session.getAttribute("personalDetails"));
			model.addAttribute("preferences", session.getAttribute("preferences"));
			return "myhome";
		}
	}
	
	@RequestMapping(value="/lifestyle",method=RequestMethod.POST)
	public String submitPreferences(@ModelAttribute LifeStyleBinding lifeStyleBinding,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession){
		String status=null;
		
		Long userId = (Long)httpSession.getAttribute("userId");
		//Member member = memberService.getMemberById(preferencesBinding.getMemberId());
		Object memberObj = httpSession.getAttribute("member");
		Member member= memberService.getMemberById(userId);
		if(memberObj!=null&& member==null)
			member = (Member) memberObj;
		
		Lifestyle lifestyle = lifestyleService.getLifeStyleByMember(member);

		if(lifestyle!=null){
			return "redirect:/lifestyle?status="+status+"?userId="+userId;
		}
				
					
		lifestyle = new Lifestyle();		
		lifestyle.setMember(member);
		lifestyle.setFavouriteMusic(lifeStyleBinding.getFavouriteMusic());
		lifestyle.setHobbiesAndIntereset(lifeStyleBinding.getHobbiesAndIntereset());
		lifestyle.setOtherHobby(lifeStyleBinding.getOtherHobby());
		lifestyle.setOtherMusic(lifeStyleBinding.getOtherMusic());
		lifestyle.setOtherSports(lifeStyleBinding.getOtherSports());
		lifestyle.setSportsFitness(lifeStyleBinding.getSportsFitness());			
		Lifestyle lifetstyleSave = lifestyleService.saveLifestyle(lifestyle);
		
		if(lifestyle!=null)
			status="success";
		member.setLifestyle(lifetstyleSave);
		
		
		PersonalDetails personalDetails = personalDetailsService.getPersonalDetailsByMember(member);
		Preferences  preferences = preferencesService.getPreferencesByMember(member);
		httpSession.setAttribute("personalDetails", personalDetails);
		httpSession.setAttribute("preferences",preferences);
		httpSession.setAttribute("lifestyle",lifeStyleBinding);
    	//model.addAttribute("personalDetails", personalDetails);
		model.addAttribute("member", member);
		model.addAttribute("preferences",preferences);
		model.addAttribute("matchList", personalDetails);
		model.addAttribute("lifestyle",lifeStyleBinding);
		
		
		return "redirect:/lifestyle?status="+status+"?userId="+userId;
		
	}
	
	

}
