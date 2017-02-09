package com.my.controllers;

import static org.hamcrest.CoreMatchers.instanceOf;

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
import com.my.model.Preferences;
import com.my.service.MemberService;
import com.my.service.PersonalDetailsService;

@Controller
public class PersonalDetailsController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PersonalDetailsService personalDetailsService;
	
	
	@RequestMapping(value="/personaldetails",method=RequestMethod.GET)
	public String initialize(Model model,@RequestParam(value="status",required=false)String status){
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
		String status=null;
		Object memberObj = httpSession.getAttribute("member");
		Member member=null;
		if(memberObj!=null)
			member = (Member) memberObj;
		PersonalDetails personaldetailsBean = new PersonalDetails();		 
	    personaldetailsBean.setMember(member);
	    personaldetailsBean.setMaritalStatus(personaldetailsModel.getMaritalStatus());
		personaldetailsBean.setHeight(personaldetailsModel.getHeight());
		personaldetailsBean.setWeight(personaldetailsModel.getWeight());
		personaldetailsBean.setGothra(personaldetailsModel.getGothra());
		personaldetailsBean.setBodyType(personaldetailsModel.getBodyType());
		personaldetailsBean.setComplexion(personaldetailsModel.getComplexion());
		personaldetailsBean.setManglik(personaldetailsModel.getManglik());
		personaldetailsBean.setDosh(personaldetailsModel.getDosh());
		personaldetailsBean.setStarSign(personaldetailsModel.getStar());
		personaldetailsBean.setRaasi(personaldetailsModel.getRaasi());
		personaldetailsBean.setEatingHabits(personaldetailsModel.getEatinghabits());
		personaldetailsBean.setDrinkingHabits(personaldetailsModel.getDrinkinghabits());
		personaldetailsBean.setSmokingHabits(personaldetailsModel.getSmokinghabits());
		personaldetailsBean.setFamilyStatus(personaldetailsModel.getFamilystatus());
		personaldetailsBean.setFamilyType(personaldetailsModel.getFamilytype());
		personaldetailsBean.setFamilyValues(personaldetailsModel.getFamilyvalue());
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
		
		httpSession.setAttribute("member", member);
		model.addAttribute("preferences", new PreferencesBinding());
		Long personalDetails = personalDetailsService.savePersonalDetails(personaldetailsBean);
		
		if(personalDetails>=1)
			status="success";
		return "redirect:/personaldetails?status="+status;
	}

}
