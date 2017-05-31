package com.my.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.binding.PreferencesBinding;
import com.my.binding.SearchBinding;
import com.my.service.PhotoService;

@Controller
public class PhotoManagerController {

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C://Website//photos";
	
    @Autowired
    private PhotoService photoService; 

	@RequestMapping(value = "/myPhotos", method = RequestMethod.GET)
	public String start(Model model, @RequestParam(value = "userId", required = true) long userId,
			@RequestParam(value = "status", required = false) String status) {
		 //List<Photo> photos = photoService.getPhotosByUserId(userId);
		 List<String> photoNames = photoService.getPhotoNamesByUserId(userId);
		 
		 model.addAttribute("photoNames", photoNames);
		if (status == null)
			return "photoUpload";
		else
			return "photoAlbum";
	}


	@RequestMapping(value = "/myPhotos", method = RequestMethod.POST)
	public String show(@RequestParam(value = "userId", required = true) long userId,
			@ModelAttribute PreferencesBinding preferencesBinding, BindingResult bindingResult, Model model,
			HttpSession httpSession) {

		model.addAttribute("search", new SearchBinding());
		return "photoalbum";
	}

	
}
