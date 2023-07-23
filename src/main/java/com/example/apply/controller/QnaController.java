package com.example.apply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor 
public class QnaController {

	@GetMapping(value = "/qna/list")
	public String List() {
		return "/qna/qnaList";
	}
	
	@GetMapping(value = "/qna/regist")
	public String Regist(){
		return "qna/qnaRegist";
	}
	
	//등록하기
	@PostMapping(value = "/qna/regist")
	public String addRegist() {
		return "qna/qnaRegist";
	}
}
