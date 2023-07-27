package com.example.apply.controller;

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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {

	private final QnaService qnaService;
	
	//게시글 리스트 보여주기
	@GetMapping(value={"/qna/list", "/qna/list/{page}"})
	public String list(Model model,QnaDto qnaDto, @PathVariable("page") Optional<Integer> page) {
		//페이징 처리 : 한 페이지당 5개씩 보여주기
		Pageable pageable = PageRequest.of(page.isPresent()?page.get():0, 5);
		Page<Qna> list = qnaService.getListPage(pageable);
		
		//model.addAttribute(String key, Object value);
		model.addAttribute("lists",list);
		//숫자 5를 maxPage라는 이름으로 view에 보낸다
		model.addAttribute("maxPage",5);
		
		return "qna/qnaList";
	}
	
	//게시글 등록 화면 보여주기
	@GetMapping(value="/qna/regist")
	public String regist() {
		return "qna/qnaRegist";
	}
	
	//게시글 등록하기
	@PostMapping(value = "/qna/regist") 		//사용자의 정보를 불러옴
	public String addRegist(@Valid QnaDto qnaDto, Principal principal) {
		                  //자동으로 qnaDto 객체의 유효성 검사
				
		String id = principal.getName();
		
		try {
			qnaService.saveQna(qnaDto,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// PostMapping + return = 처리가 2번 되어 중복 제출 되므로 redirect 사용 
		return "redirect:/qna/list";
	}
	
	//등록한 게시글 보여주기
	@GetMapping(value = {"/qna/detail", "/qna/detail/{id}"})
	public String detail(@PathVariable(name = "id") Long id,Model model) {
		
		Qna qna = qnaService.getDetailPage(id);
		
		 model.addAttribute("qna", qna); 
		 
		return "qna/qnaDetail";
	}
	
	//수정하기
	@GetMapping(value={"/qna/modify","/qna/modify/{id}"})
	public String update(@PathVariable(name = "id") Long id, Model model) {
	//Model:컨트롤러>뷰 데이터를 전달. 뷰 페이지와 폼 데이터 바인딩.
		Qna qna = qnaService.getDetailPage(id);
		
		model.addAttribute("qnaDto", new QnaDto());
		model.addAttribute("qna", qna); 
		 
		return "qna/qnaEdit";
	}
	
	//수정하기
	@PostMapping(value={"/qna/modify","/qna/modify/{id}"})
	public String update(@Valid QnaDto qnaDto, Principal principal,@PathVariable(name = "id") Long id) {
		
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

}
