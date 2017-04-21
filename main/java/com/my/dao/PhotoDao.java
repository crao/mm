package com.my.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.my.model.Photo;

@Transactional
public interface PhotoDao extends CrudRepository<Photo, Long>{
	
	public List<Photo> findByUserId(long userId);

}
