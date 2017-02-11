package com.my.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.binding.PreferencesBinding;
import com.my.binding.SearchBinding;

@Controller
public class PhotoManagerController {

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C://Website//photos";

	@RequestMapping(value = "/myPhotos", method = RequestMethod.GET)
	public String start(Model model, @RequestParam(value = "userId", required = true) long userId,
			@RequestParam(value = "status", required = false) String status) {
		if (status == null)
			return "photoUpload";
		else
			return "photoAlbum";
	}

	@PostMapping("/photo")
	public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile uploadPhoto) {

		
		
		if (uploadPhoto.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			saveUploadedFiles(Arrays.asList(uploadPhoto));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " + uploadPhoto.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/myPhotos", method = RequestMethod.POST)
	public String show(@RequestParam(value = "userId", required = true) long userId,
			@ModelAttribute PreferencesBinding preferencesBinding, BindingResult bindingResult, Model model,
			HttpSession httpSession) {

		model.addAttribute("search", new SearchBinding());
		return "photoalbum";
	}

	private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

		}
	}
}
