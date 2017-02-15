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
import com.my.binding.PersonalDetailsModel;
import com.my.binding.PreferencesBinding;
import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.service.MemberService;
import com.my.service.PersonalDetailsService;

@Controller
public class PersonalDetailsController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PersonalDetailsService personalDetailsService;
	
	
	@RequestMapping(value="/personaldetails",method=RequestMethod.GET)
	public String initialize(Model model,@RequestParam(value="status",required=false)String status, @RequestParam(value="userId",required=false)String userId){
		model.addAttribute("personaldetails", new PersonalDetailsModel());
		if(status==null)
			return "personaldetails";
		else
			return "preferences";
	}
	
	@RequestMapping(value="/personaldetails",method=RequestMethod.POST)
	public String submitPersonalDetails(@ModelAttribute PersonalDetailsModel personaldetailsModel,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession){
		
		Long userId = (Long)httpSession.getAttribute("userId");
		
		
		String status=null;
		Object memberObj = httpSession.getAttribute("member");
		Member member= memberService.getMemberById(userId);
		if(memberObj!=null&& member==null)
			member = (Member) memberObj;
		
		PersonalDetails personaldetailsBean = personalDetailsService.getPersonalDetailsByMember(member);
		
		if(personaldetailsBean!=null){
			return "redirect:/personaldetails?status="+status+"?userId="+userId;
		} 
		personaldetailsBean = new PersonalDetails();		 
	    personaldetailsBean.setMember(member);
	    personaldetailsBean.setMaritalStatus(personaldetailsModel.getMaritalStatus());
	    personaldetailsBean.setBirthTime(personaldetailsModel.getBirthTime());
	    personaldetailsBean.setBirthPlace(personaldetailsModel.getBirthPlace());
		personaldetailsBean.setHeight(personaldetailsModel.getHeight());
		personaldetailsBean.setWeight(personaldetailsModel.getWeight());
		personaldetailsBean.setGothra(personaldetailsModel.getGothra());
		personaldetailsBean.setBodyType(personaldetailsModel.getBodyType());
		personaldetailsBean.setComplexion(personaldetailsModel.getComplexion());
		personaldetailsBean.setPhysicalStatus(personaldetailsModel.getPhysicalStatus());
		personaldetailsBean.setManglik(personaldetailsModel.getManglik());
		personaldetailsBean.setDosh(personaldetailsModel.getDosh());
		personaldetailsBean.setStarSign(personaldetailsModel.getStar());
		personaldetailsBean.setRaasi(personaldetailsModel.getRaasi());
		personaldetailsBean.setEatingHabits(personaldetailsModel.getEatinghabits());
		personaldetailsBean.setDrinkingHabits(personaldetailsModel.getDrinkinghabits());
		personaldetailsBean.setSmokingHabits(personaldetailsModel.getSmokinghabits());
		personaldetailsBean.setFamilyStatus(personaldetailsModel.getFamilystatus());
		personaldetailsBean.setFamilyType(personaldetailsModel.getFamilytype());
		personaldetailsBean.setFamilyValues(personaldetailsModel.getFamilyValues());
		personaldetailsBean.setEducation(personaldetailsModel.getEducation());
		personaldetailsBean.setOccupationcategory(personaldetailsModel.getOccupationcategory());
		personaldetailsBean.setOccupation(personaldetailsModel.getOccupation());
		personaldetailsBean.setIncome(personaldetailsModel.getIncome());
		personaldetailsBean.setIncomecurrency(personaldetailsModel.getIncomecurrency());
		personaldetailsBean.setResidingcity(personaldetailsModel.getResidingcity());
		personaldetailsBean.setResidingstate(personaldetailsModel.getResidingstate());
		personaldetailsBean.setCountry(personaldetailsModel.getCountry());	
		personaldetailsBean.setFatherName(personaldetailsModel.getFatherName());
		personaldetailsBean.setMotherName(personaldetailsModel.getMotherName());
		personaldetailsBean.setCountryP(personaldetailsModel.getCountryP());
		personaldetailsBean.setResidingstateP(personaldetailsModel.getResidingstateP());
		personaldetailsBean.setResidingcityP(personaldetailsModel.getResidingcityP());
		personaldetailsBean.setCitizenship(personaldetailsModel.getCitizenship());
		
		httpSession.setAttribute("member", member);
		model.addAttribute("preferences", new PreferencesBinding());
		Long personalDetails = personalDetailsService.savePersonalDetails(personaldetailsBean);
		
		if(personalDetails>=1)
			status="success";
		return "redirect:/personaldetails?status="+status+"?userId="+userId;
	}

}
