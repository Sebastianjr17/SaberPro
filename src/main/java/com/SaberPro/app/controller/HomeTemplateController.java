package com.SaberPro.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeTemplateController {

    @GetMapping("/")
    public String homeTemplate(Model model) {
        return "home";
    }
}
