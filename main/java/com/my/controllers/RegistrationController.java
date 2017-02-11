package com.my.controllers;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.my.binding.PersonalDetailsModel;
import com.my.binding.Register;
import com.my.model.Member;
import com.my.service.MemberService;

@Controller
@SessionAttributes("userId")
public class RegistrationController {
	
	Map<String,Object> myCache = new HashMap<String,Object>();
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String initialize(Model model,@RequestParam(value="status",required=false)String status,@RequestParam(value="userId",required=false)String userId){
		model.addAttribute("personaldetails", new PersonalDetailsModel());		
		if(status==null)
			return "home";
		else
			return "personaldetails";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	//@ResponseBody
	public String register(@ModelAttribute Register register,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession) {
		if (bindingResult.hasErrors()) {
			return "home";
		}
		String message="";
		Member memberCheck = memberService.getMemberByEmail(register.getEmail());
		if(memberCheck!=null){
			message = "User already register. Cannot register again!";
			model.addAttribute("errorMessage", message);
			return "error";
		}
		
		Member member = new Member();
		member.setProfileFor(register.getProfileFor());
		member.setFirstName(register.getFirstName());
		member.setLastName(register.getLastName());
			
		StringBuilder strBuilder = new StringBuilder("");
		strBuilder.append(register.getMonthob()).append("/").append(register.getDayob())
		.append("/").append(register.getYearob());
		
		 DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		 Date dob=null;
		    try {
		        dob = df.parse(strBuilder.toString());
		        String newDateString = df.format(dob);
		        System.out.println(newDateString);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		if(dob!=null)    
			member.setDob(dob);  
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date now = new Date();
		int yearNow = Integer.parseInt(dateFormat.format(now));
		
		int yearBirth = Integer.parseInt(dateFormat.format(dob));
		
		member.setAge(yearNow - yearBirth);
		member.setGender(register.getGender());
		member.setEmail(register.getEmail());
		member.setPassword(register.getPassword());
		member.setCountryCode(Integer.parseInt(register.getCountryCode().substring(1)));
		member.setMobile(register.getMobile());
		long userId=-1;
		String status=null;
		try{
			
			member = memberService.save(member);
			if(member!=null){
				userId=member.getMemberId();
				status="success";
			}
			httpSession.setAttribute("member", member);
			httpSession.setAttribute("userId", userId);
			
			myCache.put(Long.toString(userId), member);
			
			model.addAttribute("userId", userId);
		}catch (SQLException e) {
			return "Incorrect data";
		}
		
		
		PersonalDetailsModel personalDetailsModel = new PersonalDetailsModel();
		personalDetailsModel.setUserId(userId);
		model.addAttribute("personaldetails", personalDetailsModel);
		
		return "redirect:/register?status="+status+"?userId="+userId;
	}

}
