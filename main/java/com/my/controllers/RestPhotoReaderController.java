package com.my.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.service.PhotoService;

@RestController
public class RestPhotoReaderController {
	
	 
    private static String UPLOADED_FOLDER = "C://Website//photos//";
    
    @Autowired
    private PhotoService photoService;
    
    @RequestMapping(value = "/photo/{userId}/{fileName:.+}")
    public @ResponseBody byte[] showPhoto(@PathVariable Long userId,@PathVariable String fileName) {   	
    	String photoLocation = UPLOADED_FOLDER+userId+"//" + fileName;    	
    	return readPhoto(photoLocation);
    }
    
    @RequestMapping(value="/photo/{id}/profilePhoto")
    public @ResponseBody byte[] showProfilePhoto(@PathVariable Long userId) {   
    	String fileName = photoService.getProfilePhoto(userId).getFileName();
    	String photoLocation = UPLOADED_FOLDER+userId+"//" + fileName;    	
    	return readPhoto(photoLocation);
    }
    
    
    
    private byte[] readPhoto(String photoLocation){
    	

    	byte[] b = null;
    	File photoFile = new File(photoLocation);
    	InputStream in = null;
    	try {
			in = new BufferedInputStream(new FileInputStream(photoFile));
			b = IOUtils.toByteArray(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return b;
    }

}
