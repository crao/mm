package com.my.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.my.model.Message;

public interface MessageDao extends CrudRepository<Message, Long>{
	
	List<Message> findByToMemId(long memId);

	
	public Long findSlIdByToMemIdAndFromMemId(long tomemid,long frommemid);
	
	
	@Query(value="select s.fromMemId from Message s where s.toMemId = :toMemId")
	public List<Long> findfromMemIdBytoMemId(@Param("toMemId") long fromMemId);
	
	@Query(value="select s.toMemId from Message s where s.fromMemId = :fromMemId")
	public List<Long> findtoMemIdByfromMemId(@Param("fromMemId") long toMemId);
	
}
