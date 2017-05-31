package com.my.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.binding.PersonalDetailsModel;
import com.my.binding.PreferencesBinding;
import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Preferences;
import com.my.service.MemberService;
import com.my.service.PersonalDetailsService;
import com.my.service.PreferencesService;

@Controller
public class UpDataOnProController {
	
	private static final String Null = null;

	@Autowired
	MemberService memberService;
	
	@Autowired
	PersonalDetailsService personalDetailsService;
	
	
	@RequestMapping(value="/personaldetailsedit",method=RequestMethod.GET)
	public String initialize(Model model,@RequestParam(value="status",required=false)String status, @RequestParam(value="userId",required=false)String userId){
		model.addAttribute("personaldetails", new PersonalDetailsModel());
		if(status==null)
			return "personaldetails";
		else
			return "preferences";
	}
	
	@RequestMapping(value="/personaldetailsedit",method=RequestMethod.POST)

	public String submitPersonalDetails(@ModelAttribute PersonalDetailsModel personaldetailsModel,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession){
		
		
		
		
		
		Long userId = (Long)httpSession.getAttribute("userId");	
		Member member= memberService.getMemberById(userId);	
		
		
		PersonalDetails personaldetailsBean = personalDetailsService.getPersonalDetailsByMember(member);
	
		
		if(personaldetailsModel.getMaritalStatus()!=Null || personaldetailsModel.getBirthTime()!=Null || personaldetailsModel.getBirthPlace()!= Null )
		{
	    personaldetailsBean.setMaritalStatus(personaldetailsModel.getMaritalStatus());
	    personaldetailsBean.setBirthTime(personaldetailsModel.getBirthTime());
	    personaldetailsBean.setBirthPlace(personaldetailsModel.getBirthPlace());	    
	  
		}
	
		else if(personaldetailsModel.getHeight()!= 0 || personaldetailsModel.getWeight()!= 0 
				|| personaldetailsModel.getBodyType()!=Null || personaldetailsModel.getComplexion()!=Null || personaldetailsModel.getPhysicalStatus()!=Null)
		{	
	   
		personaldetailsBean.setHeight(personaldetailsModel.getHeight());
		personaldetailsBean.setWeight(personaldetailsModel.getWeight());
	
		personaldetailsBean.setBodyType(personaldetailsModel.getBodyType());
		personaldetailsBean.setComplexion(personaldetailsModel.getComplexion());
		personaldetailsBean.setPhysicalStatus(personaldetailsModel.getPhysicalStatus());		
	
		}
		else if(personaldetailsModel.getEatinghabits()!= Null)
		{	
			personaldetailsBean.setEatingHabits(personaldetailsModel.getEatinghabits());
			personaldetailsBean.setDrinkingHabits(personaldetailsModel.getDrinkinghabits());
			personaldetailsBean.setSmokingHabits(personaldetailsModel.getSmokinghabits());
		  
		
		}
		else if(personaldetailsModel.getEducation()!=Null||personaldetailsModel.getOccupationcategory()!=Null||personaldetailsModel.getOccupation()!=Null||
				personaldetailsModel.getIncome()!=Null||personaldetailsModel.getIncomecurrency()!=Null)
		{
			personaldetailsBean.setEducation(personaldetailsModel.getEducation());
			personaldetailsBean.setOccupationcategory(personaldetailsModel.getOccupationcategory());
			personaldetailsBean.setOccupation(personaldetailsModel.getOccupation());
			personaldetailsBean.setIncome(personaldetailsModel.getIncome());
			personaldetailsBean.setIncomecurrency(personaldetailsModel.getIncomecurrency());
			
		}
		else if(personaldetailsModel.getManglik()!=Null||personaldetailsModel.getDosh()!=Null||personaldetailsModel.getStar()!=Null
				|| personaldetailsModel.getGothra()!=Null||	personaldetailsModel.getRaasi()!=Null)
		{
			personaldetailsBean.setManglik(personaldetailsModel.getManglik());
			personaldetailsBean.setDosh(personaldetailsModel.getDosh());
			personaldetailsBean.setGothra(personaldetailsModel.getGothra());
			personaldetailsBean.setStarSign(personaldetailsModel.getStar());
			personaldetailsBean.setRaasi(personaldetailsModel.getRaasi());
			
		}
		PersonalDetails personalDetails = personalDetailsService.savePersonalDetails(personaldetailsBean);
	return "redirect:/profile?userId="+userId;
	}

}
