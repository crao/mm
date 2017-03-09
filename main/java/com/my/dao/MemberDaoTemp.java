package com.my.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.my.model.Member;


@Repository
public class MemberDaoTemp {
	
	
	private static Map<Long,Member> members;
	
	
	
	public Collection<Member> getAllMembers(){
		return members.values();
	}
	
	
}
