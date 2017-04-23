package com.my.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.my.model.Message;

public interface MessageDao extends CrudRepository<Message, Long>{
	
	List<Message> findByToMemId(long memId);

}
