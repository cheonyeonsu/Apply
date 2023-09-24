package com.example.apply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping(value = "/")
	public String main(Model model) {
		model.addAttribute("headerBigMsg","MAIN");
		model.addAttribute("imgurl","/img/home-bg.jpg");
		model.addAttribute("headerMsg","방문을 환영합니다.");
		return "main";
	}
}
