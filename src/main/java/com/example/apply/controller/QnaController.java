package com.example.apply.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apply.dto.QnaDto;
import com.example.apply.entity.Qna;
import com.example.apply.service.QnaService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {
	
	private final QnaService qnaService;
	
	@GetMapping(value={"/qna/list", "/qna/list/{page}"})
	public String list(Model model,QnaDto qnaDto, @PathVariable("page") Optional<Integer> page) {
		//한 페이지당 5개씩 보여주기
		Pageable pageable = PageRequest.of(page.isPresent()?page.get():0, 5);
		
		Page<Qna> list = qnaService.getListPage(pageable);
		
		model.addAttribute("lists",list);
		

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
		
		return "qna/qnaRegist";
	}
}
