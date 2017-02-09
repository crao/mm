package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.PreferencesDao;
import com.my.model.Member;
import com.my.model.Preferences;

@Service
public class PreferencesService {
	
	@Autowired
	private PreferencesDao preferencesDao;

	public long savePreferences(Preferences preferences) {
		return preferencesDao.save(preferences).getId();		
	}
	
	public Preferences getPreferencesByMember(Member member){
		return preferencesDao.findByMember(member);
	}
	
    public Preferences getPreferencesByMemberId(long userId){
    	return preferencesDao.findByUserId(userId);
    }

}
