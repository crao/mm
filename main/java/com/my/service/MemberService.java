package com.my.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.MemberDao;
import com.my.model.Member;


@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	
	
	public Member save(Member member) throws SQLException {		
		return memberDao.save(member);
	}
	
	public Member getMemberByEmail(String email){
		return memberDao.findByEmail(email);
	}
	
	public Member getMemberById(long id){
		return memberDao.findByMemberId(id);
	}
	
	public List<Member> getByMemberIds(List<Long> ids){
		return memberDao.findByMemberIdIn(ids);
	}
	public List<Long> findMember() {
		// TODO Auto-generated method stub
		return memberDao.findMemberByMemId();
	}
}
