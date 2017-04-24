package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.VisitDao;
import com.my.model.Visit;
 

@Service
public class VisitWatcherService {
	
	@Autowired
	private VisitDao visitDao;
	
	public boolean save(Visit visit){
		boolean save = false;
		Visit v = visitDao.save(visit);
		if(v!=null){
			save=true;
		}
		
		return save;
	}
	
	public List<Long> findVistors(long memId){
		return visitDao.findVisitorByMemId(memId);
	}
	
	public Visit findVistor(long vistorId){
		return visitDao.findByVisitor(vistorId);
	}
	
	public List<Visit> findVisits(long memId){
		return visitDao.findByMemId(memId);
	}

}
