package com.my.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.model.Photo;
import com.my.model.UploadModel;
import com.my.service.PhotoService;

@RestController
public class RestPhotoUploadController {

    private final Logger logger = LoggerFactory.getLogger(RestPhotoUploadController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://Website//photos//";
    
    @Autowired
    private PhotoService photoService; 

    //Single file upload
    @PostMapping("/photo1/upload")
    // If not @RestController, uncomment this
    //@ResponseBody
    public String uploadFile(
            @RequestParam("file") MultipartFile uploadfile,
            HttpSession httpSession) {

    	Long userId = (Long)httpSession.getAttribute("userId");
        logger.debug("Single file upload!");

        if (uploadfile.isEmpty()) {
            return "error";
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfile),userId);

        } catch (IOException e) {
            return "error";
        }

        return "redirect:/myPhotos?userId=" + userId;

    }

    // Multiple file upload
    @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) {

        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfiles),null);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }

    // maps html form to a Model
    @PostMapping("/api/upload/multi/model")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {

        logger.debug("Multiple file upload! With UploadModel");

        try {

            saveUploadedFiles(Arrays.asList(model.getFiles()),null);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

    }

    //save file
    private void saveUploadedFiles(List<MultipartFile> files,Long userId) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
           
           /* Resource resource = new ClassPathResource("mm");
            URI uri = resource.getURI();
            File temp = new File(uri);*/
          File userDir = new File(UPLOADED_FOLDER+userId+"//");
          if(!userDir.exists()){
        	  userDir.mkdirs();
          }
            
            byte[] bytes = file.getBytes();
            Path path = Paths.get(userDir +"//"+ file.getOriginalFilename());
            Files.write(path, bytes);
            
            Photo photo = new Photo();
            photo.setFileName(file.getOriginalFilename());
            photo.setUserId(userId);
            long photoId = photoService.save(photo);
            

        }

    }
}
