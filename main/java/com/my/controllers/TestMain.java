package com.my.controllers;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestMain {

	public static void main(String[] args) {
		
		 DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		 Date dob=null;
		    try {
		        dob = df.parse("01/01/1990");
		        String newDateString = df.format(dob);
		        System.out.println(newDateString);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date now = new Date();
		int yearNow = Integer.parseInt(dateFormat.format(now));
		
		int yearBirth = Integer.parseInt(dateFormat.format(dob));
		
		System.out.println(yearNow - yearBirth);
		
		String test = "test1";

		String[] out = test.split(",");
		
		List<String> list = Arrays.asList(out);
		
		System.out.println(list);
		
		String rootPath = "C:/Website/photos";
		File dir = new File(rootPath + File.separator + "tmpFiles");
		boolean isTrue = dir.getAbsoluteFile().exists();
		if (!isTrue)
			dir.mkdirs();
	}

}
