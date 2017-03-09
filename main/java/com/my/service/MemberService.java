package com.my.service;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.MemberDao;
import com.my.dao.MemberDaoTemp;
import com.my.model.Member;


@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	MemberDaoTemp memberDaoTemp;
	
	public Member save(Member member) throws SQLException {		
		return memberDao.save(member);
	}
	
	public Member getMemberByEmail(String email){
		return memberDao.findByEmail(email);
	}
	
	public Member getMemberById(long id){
		return memberDao.findByMemberId(id);
	}

	
	public Member getAllMembers(){
		return (Member) memberDaoTemp.getAllMembers();
	}
	
	
}
