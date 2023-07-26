package com.example.apply.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apply.dto.QnaDto;
=======
import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.apply.dto.QnaDto;
import com.example.apply.entity.Qna;
import com.example.apply.service.QnaService;

>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {
<<<<<<< HEAD

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
	
=======
	
	private final QnaService qnaService;
	
	@GetMapping(value={"/qna/list", "/qna/list/{page}"})
	public String list(Model model,QnaDto qnaDto, @PathVariable("page") Optional<Integer> page) {
		//한 페이지당 5개씩 보여주기
		Pageable pageable = PageRequest.of(page.isPresent()?page.get():0, 5);
		
		Page<Qna> list = qnaService.getListPage(pageable);
		
		//숫자 5를 maxPage라는 이름으로 view에 보낸다
		//model.addAttribute(String key, Object value);
		model.addAttribute("lists",list);
		model.addAttribute("maxPage",5);
		
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
		
		return "redirect:/qna/list";
	}
	
	
	@GetMapping(value = {"/qna/detail", "/qna/detail/{id}"})
	public String detail(@PathVariable(name = "id") Long id,Model model) {
		
		Qna qna = qnaService.getDetailPage(id);
		
		 model.addAttribute("qna", qna); 
		 
		return "qna/qnaDetail";
	}
	
	//수정하기
	@GetMapping(value={"/qna/modify","/qna/modify/{id}"})
	public String edit(@PathVariable(name = "id") Long id, Model model) {
		Qna qna = qnaService.getDetailPage(id);
		
		model.addAttribute("qnaDto", new QnaDto());
		model.addAttribute("qna", qna); 
		 
		return "qna/qnaEdit";
	}
	
	@PostMapping(value={"/qna/modify","/qna/modify/{id}"})
	public String edit(@Valid QnaDto qnaDto, Principal principal,@PathVariable(name = "id") Long id) {
		
		qnaService.updateQna(id, qnaDto);
		
		//redirect : 지정된 다른 URL로 재요청하라고 지시
		return "redirect:/qna/detail/" + id;
	}
	
	//삭제하기 
	@DeleteMapping("/qna/delete/{id}")
	public @ResponseBody ResponseEntity deleteQna
	(@PathVariable ("id") Long id,Principal principal) {
		
		qnaService.deleteQna(id);
		
		return new ResponseEntity<Long>(id,HttpStatus.OK);
	}
>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
}
