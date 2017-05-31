package com.my.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.my.model.ShortList;
import com.my.model.Visit;

@Transactional
public interface ShortListDao extends CrudRepository<ShortList, Long>{
	
	@Query(value="select s.slId from ShortList s where s.memId = :memId")
	public List<Long> findSlIdByMemId(@Param("memId") long memId);
	
	public Long findSlIdBySlIdAndMemId(long slid,long memid);
 
	 @Query(value="select s.memId from ShortList s where s.slId = :memId")
		public List<Long>  findSlIdByslId(@Param("memId") long slId);
	
}
