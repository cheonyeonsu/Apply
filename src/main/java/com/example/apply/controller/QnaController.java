package com.example.apply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apply.dto.QnaDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor //생성자 주입
public class QnaController {

	@GetMapping(value="/qna/list")
	public String list() {
		return "qna/qnaList";
	}
	
	@GetMapping(value="/qna/regist")
	public String regist() {
		return "qna/qnaRegist";
	}
	
	//게시글 등록하기
	@PostMapping(value="/qna/regist") 
	public String addRegist(@Valid QnaDto qnaDto) {
		return "qna/qnaRegist";
	}
}
