package com.my.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class MainController {
	
	@RequestMapping("/")
    String home() {
        return "home";
    }


}
