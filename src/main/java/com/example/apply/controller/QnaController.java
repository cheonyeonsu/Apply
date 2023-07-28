package com.example.apply.controller;

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
@RequiredArgsConstructor //HTTP GET 요청을 처리하는 메서드를 맵핑
public class QnaController {
	
	@GetMapping(value={"/qna/list", "/qna/list/{page}"})
	public String list(Model model, QnaDto qnaDto,
			@PathVariable("page")Optional<Integer>page) {
		
		private final QnaService qnaService;
		
		Pageable pageable = PageRequest.of(page.isPresent()?page.get():0,5);
		Page<Qna>list = qnaService.getListPage(pageable);
		model.addAttribute("lists",list);
		model.addAttribute("maxPage",5);
		
		return "qna/qnaList";
	}
	
	@GetMapping(value="/qna/regist")
	public String regist() {
		return "qna/qnaRegist";
	}
	
	//등록하기
	@PostMapping(value="/qna/regist") 
	public String addRegist(@Valid QnaDto qnaDto) {
		
		
		return "qna/qnaRegist";
	}
}