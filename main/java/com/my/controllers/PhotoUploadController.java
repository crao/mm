package com.my.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PhotoUploadController {
	
	@RequestMapping(value = "/photoUpload", method = RequestMethod.GET)
	public String init(){
		return "uploadPhoto";
	}
	
	
	@RequestMapping(value = "/photoUpload", method = RequestMethod.POST)
	public String photoUploadHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file){
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = "C://Website/photos";
				File dir = new File(rootPath + File.separator + "tmpFiles");
				boolean isTrue = dir.getAbsoluteFile().exists();
				if (!isTrue)
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				

				return "photoupload"; // BB
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
		
	
	}

}
