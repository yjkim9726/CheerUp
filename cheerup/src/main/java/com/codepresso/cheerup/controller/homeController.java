package com.codepresso.cheerup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class homeController {
    @GetMapping("/home")
    public String feedbackBoard() {
        return "home/homeMain";
    }
}
