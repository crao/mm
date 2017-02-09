package com.my.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.my.model.Member;
import com.my.model.PersonalDetails;

@Transactional
public interface PersonalDetailsDao extends CrudRepository<PersonalDetails, Long>{
	
	public PersonalDetails findByMember(Member member);
	
	public PersonalDetails findByUserId(long userId);

}
