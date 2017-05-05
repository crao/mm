package com.my.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.my.model.Lifestyle;
import com.my.model.Member;

@Transactional
public interface LifeStyleDao  extends CrudRepository<Lifestyle, Long> {

	Lifestyle findByMember(Member member);

	


	
	
}
