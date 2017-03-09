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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.my.binding.Register;
import com.my.model.Member;
import com.my.service.MemberService;

@Controller

public class RegistrationController {
	
	Map<String,Object> myCache = new HashMap<String,Object>();
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String initialize(Model model){
		
		
			return "home";
	
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(@ModelAttribute Register register,
			BindingResult bindingResult,
			Model model,
			HttpSession httpSession) throws SQLException {
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
		member.setBrandName(register.getBrandName());
		member.setEmail(register.getEmail());
		member.setPassword(register.getPassword());	
		member.setMobileNo(register.getMobileNo());
		member.setWorkingHours(register.getWorkingHours());
		member.setWorkingDays(register.getWorkingDays());
		member.setCity(register.getCity());
	
		member.setCategory(register.getCategory());	
	     member = memberService.save(member);	
		
		
		
		return "Succesful";
	}

}
