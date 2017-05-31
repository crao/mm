package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.PersonalDetailsDao;
import com.my.model.Member;
import com.my.model.PersonalDetails;

@Service
public class PersonalDetailsService {
	
	@Autowired
	PersonalDetailsDao personalDetailsDao;
	
	
	public PersonalDetails savePersonalDetails(PersonalDetails personalDetails){
		return personalDetailsDao.save(personalDetails);		
	}


	public PersonalDetails getPersonalDetailsByMemberId(long memberId){
		return personalDetailsDao.findByUserId(memberId);
	}
	
	public PersonalDetails getPersonalDetailsByMember(Member member){
		return personalDetailsDao.findByMember(member);
	}
	
	public void getMembers(){
		//CriteriaQuery<PersonalDetails>
	}
}
