package com.my.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.my.model.Visit;

@Transactional
public interface VisitDao extends CrudRepository<Visit, Long>{
	
	/**
	 * 
	 * @param visitorUserId
	 * @return
	 */
	public List<Visit> findByMemId(long memId);
	
	public Visit findByVisitor(long vistor);

}
