package com.SaberPro.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

	@GetMapping("/")
	public String HomeTemplate(Model model) {
		return "index";
	}

	@GetMapping("/index")
	public String LoginTemplate(Model model) {
		return "index";
	}

}
