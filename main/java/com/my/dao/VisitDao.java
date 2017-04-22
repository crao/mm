package com.my.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.my.model.Visit;

@Transactional
public interface VisitDao extends CrudRepository<Visit, Long>{
	
	/**
	 * 
	 * @param visitorUserId
	 * @return
	 */
	//public List<Visit> findByMemId(long memId);
	
	public Visit findByVisitor(long vistor);
	
	@Query(value="select v.visitor from Visit v where v.memId= :memId")
	public List<Long> findVisitorByMemId(@Param("memId") long memId);

}
