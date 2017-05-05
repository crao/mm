package com.my.service;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.LifeStyleDao;
import com.my.dao.MemberDao;
import com.my.model.Lifestyle;
import com.my.model.Member;


@Service
public class LifeStyleService {
	
	@Autowired
	private LifeStyleDao lifeStyleDao;

	public Lifestyle getLifeStyleByMember(Member member) {
		return lifeStyleDao.findByMember(member);
	}

	public Lifestyle saveLifestyle(Lifestyle lifestyle) {
		// TODO Auto-generated method stub
		return lifeStyleDao.save(lifestyle);		
	}
	
	
}
