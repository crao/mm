package com.my.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.my.model.Photo;

@Transactional
public interface PhotoDao extends CrudRepository<Photo, Long>{
	
	public List<Photo> findByUserId(long userId);
	
	@Query(value="select fileName from Photo p where p.userId = :userId")
	public List<String> findFileNameByUserId(@Param("userId") long userId);
	
	@Query(value="select p from Photo p where p.userId = :userId and p.type = :type")
	public List<Photo> findByUserIdAndType(@Param("userId") long userId,@Param("type") String type);

}
