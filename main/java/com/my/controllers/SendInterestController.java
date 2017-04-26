package com.my.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.model.Message;
import com.my.model.ShortList;
import com.my.service.MemberService;
import com.my.service.MessageService;
import com.my.service.ShortListService;

@Controller
public class SendInterestController {
	

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/sendinterest",method=RequestMethod.GET)
	public ResponseEntity start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,
			@RequestParam(value="message",required=false)String visId,
			@RequestParam(value="fromMemId",required=false)String fromMemId,
			@RequestParam(value="toMemId",required=false)String toMemId,HttpSession session){
		
		
		
		long tomemid = Long.parseLong(toMemId);
		long frommemid = Long.parseLong(fromMemId);
		
		Long s = messageService.findShortListByToMemIdAndFromMemId(tomemid, frommemid);
		
		if(s == null){
			
			Message message = new Message();
			message.setToMemId(tomemid);
			message.setFromMemId(frommemid);
			message.setMessage("is interested in your profile. Would you like to communicate further?");
			
		
			messageService.save(message);
		}
		else
		{
			return new ResponseEntity("You send interest already", new HttpHeaders(), HttpStatus.OK);
		}
		return new ResponseEntity("Successfully Send Interest " + tomemid + " by " + frommemid, new HttpHeaders(), HttpStatus.OK);
		
		
	}
	
	
}
