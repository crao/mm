package com.my.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestShortListController {
	
	@GetMapping("/shortList")
	public ResponseEntity<?> shortList(@RequestParam(value = "slId", required = true) long slId,
			@RequestParam(value = "userId", required = true) long userId,HttpSession session){
		
		System.out.println("#*#*#*#*#*#*#*#*#");
		System.out.println("USER ID: "+userId+" SlId: "+slId);
		System.out.println("#*#*#*#*#*#*#*#*#");
		return null;
	}

}
