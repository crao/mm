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
public class PreferencesController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PreferencesService preferencesService;
	
	@Autowired
	PersonalDetailsService personalDetailsService;
	

	@Autowired
	MatchDao matchDao;
	
	
	
	@RequestMapping(value="/preferences",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,@RequestParam(value="userId",required=false)String userId,HttpSession session){
		if(status==null)
			return "preferences";
		else{
			model.addAttribute("member",session.getAttribute("member"));
			model.addAttribute("personalDetails",session.getAttribute("personalDetails"));
			model.addAttribute("preferences", session.getAttribute("preferences"));
			return "lifestyle";
		}
	}
	
	@RequestMapping(value="/preferences",method=RequestMethod.POST)
	public String submitPreferences(@ModelAttribute PreferencesBinding preferencesBinding,
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
		
		Preferences preferences = preferencesService.getPreferencesByMember(member);

		if(preferences!=null){
			return "redirect:/preferences?status="+status+"?userId="+userId;
		}
				
		preferences = new Preferences();		
		preferences.setMember(member);
		preferences.setFromAge(preferencesBinding.getFromAge());
		preferences.setToAge(preferencesBinding.getToAge());
		preferences.setFromHeight(preferencesBinding.getFromHeight());
		preferences.setToHeight(preferencesBinding.getToHeight());
		preferences.setHaveChildren(preferencesBinding.getHaveChildren());
		preferences.setMaritalStatus(preferencesBinding.getMaritalStatus());
		preferences.setPhysicalStatus(preferencesBinding.getPhysicalStatus());
		preferences.setEatingHabits(preferencesBinding.getEatingHabits());
		preferences.setDrinkingHabits(preferencesBinding.getDrinkingHabits());
		preferences.setSmokingHabits(preferencesBinding.getSmokingHabits());
		preferences.setManglik(preferencesBinding.getManglik());
		preferences.setStar(preferencesBinding.getStar());
		preferences.setRaasi(preferencesBinding.getRaasi());
		preferences.setEducation(preferencesBinding.getEducation());
		preferences.setOccupationcategory(preferencesBinding.getOccupationcategory());
		preferences.setOccupation(preferencesBinding.getOccupation());
		preferences.setIncomecurrency(preferencesBinding.getIncomecurrency());
		preferences.setIncome(preferencesBinding.getIncome());
		preferences.setCountry(preferencesBinding.getCountry());
		preferences.setState(preferencesBinding.getState());
		preferences.setResidingCity(preferencesBinding.getResidingCity());
		preferences.setCountryP(preferencesBinding.getCountryP());
		preferences.setResidingstateP(preferencesBinding.getResidingstateP());
		preferences.setResidingCityP(preferencesBinding.getResidingCityP());
		preferences.setCitizenship(preferencesBinding.getCitizenship());
		preferences.setGothra(preferencesBinding.getGothra());
		
		Preferences preferencesSave = preferencesService.savePreferences(preferences);
		if(preferencesSave!=null)
			status="success";
		member.setPreferences(preferencesSave);
		
		List<Object[]> results = matchDao.matches(member,preferences);
		
		for(Object[] result:results){
			System.out.println(result[0]);
			System.out.print(" "+result[1]);
			System.out.print(" "+result[2]);
			System.out.print(" "+result[3]);
			System.out.print(" "+result[1]);
			System.out.print(" "+result[4]);
			System.out.print(" "+result[5]);
			System.out.print(" "+result[6]);
			System.out.print(" "+result[7]);
		}
		
		PersonalDetails personalDetails = personalDetailsService.getPersonalDetailsByMember(member);
		
		httpSession.setAttribute("personalDetails", personalDetails);
		httpSession.setAttribute("preferences",preferencesBinding);
    	//model.addAttribute("personalDetails", personalDetails);
		model.addAttribute("member", member);
		model.addAttribute("preferences",preferencesBinding);
		model.addAttribute("matchList", personalDetails);
		
		
		return "redirect:/preferences?status="+status+"?userId="+userId;
		
	}
	
	

}
