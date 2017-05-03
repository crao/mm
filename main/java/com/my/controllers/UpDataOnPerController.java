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

import com.my.binding.PreferencesBinding;
import com.my.dao.MatchDao;
import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Preferences;
import com.my.service.MemberService;
import com.my.service.PersonalDetailsService;
import com.my.service.PreferencesService;

@Controller
public class UpDataOnPerController{
	
	private static final String Null = null;

	@Autowired
	MemberService memberService;
	
	@Autowired
	PreferencesService preferencesService;
	
	@Autowired
	PersonalDetailsService personalDetailsService;
	

	@Autowired
	MatchDao matchDao;
	
	
	
	@RequestMapping(value="/preferencesedit",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,@RequestParam(value="userId",required=false)String userId,HttpSession session){
		if(status==null)
			return "preferences";
		else{
			model.addAttribute("member",session.getAttribute("member"));
			model.addAttribute("personalDetails",session.getAttribute("personalDetails"));
			model.addAttribute("preferences", session.getAttribute("preferences"));
			return "profile";
		}
	}
	
	@RequestMapping(value="/preferencesedit",method=RequestMethod.POST)
	public String submitPreferences(@ModelAttribute PreferencesBinding preferencesBinding,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession){
		
		
		Long userId = (Long)httpSession.getAttribute("userId");
		//Member member = memberService.getMemberById(preferencesBinding.getMemberId());
		
		Member member= memberService.getMemberById(userId);
	
		
		Preferences preferences = preferencesService.getPreferencesByMember(member);			

		
		if(preferencesBinding.getFromAge() != 0 || preferencesBinding.getToAge() != 0 || preferencesBinding.getFromHeight() != 0 ||
				preferencesBinding.getToHeight() != 0 || preferencesBinding.getHaveChildren() != Null ||  preferencesBinding.getMaritalStatus() != Null
				|| preferencesBinding.getPhysicalStatus() != Null)
		{
		preferences.setFromAge(preferencesBinding.getFromAge());
		preferences.setToAge(preferencesBinding.getToAge());
		preferences.setFromHeight(preferencesBinding.getFromHeight());
		preferences.setToHeight(preferencesBinding.getToHeight());
		preferences.setHaveChildren(preferencesBinding.getHaveChildren());
		preferences.setMaritalStatus(preferencesBinding.getMaritalStatus());
		preferences.setPhysicalStatus(preferencesBinding.getPhysicalStatus());
		
		}
		
		else if(preferencesBinding.getEatingHabits()!=Null || preferencesBinding.getDrinkingHabits() != Null|| preferencesBinding.getSmokingHabits()!= Null )
		{
			preferences.setEatingHabits(preferencesBinding.getEatingHabits());
			preferences.setDrinkingHabits(preferencesBinding.getDrinkingHabits());
			preferences.setSmokingHabits(preferencesBinding.getSmokingHabits());
		}
		
		else if(preferencesBinding.getEducation()!=Null || preferencesBinding.getOccupationcategory() != Null || preferencesBinding.getOccupation()!=Null || 
				preferencesBinding.getIncomecurrency() != Null || preferencesBinding.getIncome() != 0)
		{
			preferences.setEducation(preferencesBinding.getEducation());
			preferences.setOccupationcategory(preferencesBinding.getOccupationcategory());
			preferences.setOccupation(preferencesBinding.getOccupation());
			preferences.setIncomecurrency(preferencesBinding.getIncomecurrency());
			preferences.setIncome(preferencesBinding.getIncome());
		}
		else if(preferencesBinding.getGothra()!=Null || preferencesBinding.getManglik() != Null ||
				preferencesBinding.getStar() != Null || preferencesBinding.getRaasi() != Null)
		{
			preferences.setGothra(preferencesBinding.getGothra());
			preferences.setManglik(preferencesBinding.getManglik());
			preferences.setStar(preferencesBinding.getStar());
			preferences.setRaasi(preferencesBinding.getRaasi());
		}
		Preferences preferencesSave = preferencesService.savePreferences(preferences);
		return "redirect:/profile?userId="+userId;
		
	}
	
	

}
