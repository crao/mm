package com.my.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.my.model.Member;
import com.my.model.Preferences;

@Transactional
public interface PreferencesDao extends CrudRepository<Preferences, Long>{
	
	public Preferences findByMember(Member member);
	
	public Preferences findByUserId(long userId);

}
