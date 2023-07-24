package com.example.apply.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apply.dto.QnaDto;
import com.example.apply.service.QnaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {

	private final QnaService qnaService;
	
	@GetMapping(value="/qna/list")
	public String list() {
		return "qna/qnaList";
	}
	
	@GetMapping(value="/qna/regist")
	public String regist() {
		return "qna/qnaRegist";
	}
	
	@PostMapping(value = "/qna/regist")
	public String addRegist(@Valid QnaDto qnaDto, Principal principal) {
		
		String id = principal.getName();
		
		try {
			qnaService.saveQna(qnaDto,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "qna/qnaList";
	}
}
