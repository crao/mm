package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.ShortListDao;
import com.my.model.ShortList;

@Service
public class ShortListService {
	
	@Autowired
	private ShortListDao shortListDao;
	
	public List<Long> getShortListedByMemId(long memId){
		return shortListDao.findSlIdByMemId(memId);
	}
	
	public Long save(ShortList shortList){
		return shortListDao.save(shortList).getId();
	}
	
	public Long findShortListBySlIdAndMemId(long sLId, long memId){
		return shortListDao.findSlIdBySlIdAndMemId(sLId, memId);
	}
	
	public List<Long> shortListsByMemId(long memId){
		return shortListDao.findSlIdByMemId(memId);
	}

}
