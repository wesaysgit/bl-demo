package com.es.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeartController {

    @GetMapping("/heart")
    public String heart() {
        return "/index.html";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/ss")
    public String ss(){
        return "redirect:index.html";
    }

    @GetMapping("/cc")
    public String cc(){
        return "redirect:index.html";
    }





}
