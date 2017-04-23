package com.my.controllers;

import static org.mockito.Matchers.longThat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.model.Member;
import com.my.model.ShortList;
import com.my.service.MemberService;
import com.my.service.ShortListService;

@Controller
public class WhoShortlistMyProfileController {
	
	@Autowired
	private ShortListService shortListService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/shortList1")
	public ResponseEntity<?> shortList(@RequestParam(value = "slId", required = false) String slId,
			@RequestParam(value = "userId", required = false) String userId,HttpSession session){	
		System.out.println("#*#*#*#*#*#*#*#*#");
		System.out.println("USER ID: "+userId+" SlId: "+slId);
		System.out.println("#*#*#*#*#*#*#*#*#");
		
		long slid = Long.parseLong(slId);
		long userid = Long.parseLong(userId);
		
		Long s = shortListService.findShortListBySlIdAndMemId(slid, userid);
		
		if(s == null){
		
			ShortList shortList = new ShortList();
			shortList.setSlId(Long.parseLong(slId));
			shortList.setMemId(Long.parseLong(userId));
			shortList.setShortListedOn(new Date());
		
			shortListService.save(shortList);
		}
		
		return new ResponseEntity("Successfully shortlisted" + slid + " by " + userid, new HttpHeaders(), HttpStatus.OK);

	}
	@RequestMapping(value="/whoShortListMyprofile",method=RequestMethod.GET)
	public String start(Model model,@RequestParam(value="status",required=false)String status,
			@RequestParam(value="userId",required=false)String userId,HttpSession session){
		
		  List<Long> shortList = shortListService.shortListsByslId(Long.parseLong(userId));
		  List<Member> whoShortListedMembers = memberService.getByMemberIds(shortList);
		  model.addAttribute("whoShortListedMembers", whoShortListedMembers);
		
		  return "whoShortListMyprofile";
		
	}
	
	
	
	

}
