package com.example.apply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apply.dto.QnaDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {

	//게시글 화면 보여줌
	@GetMapping(value = "/qna/list")
	public String list() {
		return "qna/qnaList";
	}
	
	//게시글 등록 화면 보여줌
	@GetMapping(value = "/qna/regist")
	public String regist() {
		return "qna/regist";
	}
	
	//게시글 등록
	@PostMapping(value = "/qna/regist")
	public String addRegist(@Valid QnaDto qndDto) {
	
		
		//post요청 처리 후 다른 페이지로 이동하기 위해 redirect사용.
		return "redirect:qna/regist";
		
	}
	
}
