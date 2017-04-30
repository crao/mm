package com.my.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.my.model.Member;
import com.my.model.Photo;
import com.my.service.MemberService;
import com.my.service.PhotoService;

@Controller
public class PhotoUploadController {
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://Website//photos//";
    
    @Autowired
    private PhotoService photoService; 
    
    @Autowired
    private MemberService memberService;
	
	@RequestMapping(value = "/photoUpload", method = RequestMethod.GET)
	public String init(@RequestParam(value="albumId",required=false) String albumId){
		return "uploadPhoto";
	}
	
	
	@RequestMapping(value = "/photoUpload", method = RequestMethod.POST)
	public String photoUploadHandler(@RequestParam("name") String name, @RequestParam("albumId") Long albumId,
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
	
	
	
    


    //Single file upload
    @PostMapping("/photo/upload")
    // If not @RestController, uncomment this
    //@ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile,@RequestParam("userId") String userId,@RequestParam("main") String main,
            HttpSession httpSession) {

    	//Long userid = Long.parseLong(userId);
    	Long userid = (Long)httpSession.getAttribute("userId");
    

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            List<Photo> photos = saveUploadedFiles(Arrays.asList(uploadfile),userid,main);
            Member member = (Member) httpSession.getAttribute("member");
            member.setPhotos(photos);
            memberService.save(member);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }
    

    //save file
    private List<Photo> saveUploadedFiles(List<MultipartFile> files,Long userId,String main) throws IOException {
    	
    	StringBuilder stringBuilder = new StringBuilder(UPLOADED_FOLDER);
    	stringBuilder.append(userId).append("//");

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
           
           /* Resource resource = new ClassPathResource("mm");
            URI uri = resource.getURI();
            File temp = new File(uri);*/
            File userDir = null;
          /*  if(albumId!=null && !albumId.equals("")){
            	//stringBuilder.append(albumId);
        	}*/
            userDir = new File(stringBuilder.toString());
            if(!userDir.exists()){
            	userDir.mkdirs();
            }
            
            byte[] bytes = file.getBytes();
            Path path = Paths.get(userDir +"//"+ file.getOriginalFilename());
            Files.write(path, bytes);
            
            Photo photo = new Photo();
            photo.setFileName(file.getOriginalFilename());
            photo.setUserId(userId);
            if(Boolean.valueOf(main)){
            	photo.setType("profile");
            }
            long photoId = photoService.save(photo);
            

        }
        
        return photoService.getPhotosByUserId(userId);

    }


}
