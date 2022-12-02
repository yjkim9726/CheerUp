package com.codepresso.cheerup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    @GetMapping("/")
    public String test(){
        return "home/homeMain";
    }

    @GetMapping("/inner-page")
    public String test2(){
        return "inner-page";
    }

    @GetMapping("/index")
    public String test3(){
        return "index";
    }

//    @GetMapping("/freeboard")
//    public String freeboard() {
//        return "freeboard";
//    }
}
