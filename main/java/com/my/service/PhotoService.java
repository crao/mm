package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.PhotoDao;
import com.my.model.Photo;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoDao photoDao;
	
	public List<Photo> getPhotosByUserId(long userId){
		return photoDao.findByUserId(userId);
	}
	
	public List<String> getPhotoNamesByUserId(long userId){
		return photoDao.findFileNameByUserId(userId);
	}
	
	public Photo getProfilePhoto(long userId){
		List<Photo> profilePhotos = photoDao.findByUserIdAndType(userId, "profile");
		if(profilePhotos != null){
			return profilePhotos.get(0);
		}
		return null; 
	}
	
	public long save(Photo photo){
		return photoDao.save(photo).getId();
	}

}
