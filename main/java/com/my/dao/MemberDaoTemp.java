package com.my.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.my.model.Member;


@Repository
public class MemberDaoTemp {
	
	
	private static Map<Long,Member> members;
	
	static{
		members = new HashMap<Long,Member>();
		
		members.put((long) 1, new Member(1, "Ram", "L", 29, "M"));
		members.put((long) 2, new Member(2, "B", "L", 25, "F"));
		members.put((long) 3, new Member(3, "C", "L", 24, "M"));
	}
	
	public Collection<Member> getAllMembers(){
		return members.values();
	}
	
	public Member getMemberById(long id){
		return members.get(id);
	}

}
