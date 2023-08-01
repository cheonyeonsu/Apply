package com.example.apply.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.Subject;
import com.example.apply.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SubjectController {

	private final SubjectService subjectService;

	//과목 리스트 보여주기, 페이지 매핑.
	@GetMapping(value={"/subject/list", "/subject/list/{page}"})
	public String subjectList(Model model, SubjectSearchDto subjectSearchDto, @PathVariable("page") Optional<Integer> page ) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5); 
		Page<SubjectDto> subjectPage = subjectService.getSubjectPage(subjectSearchDto, pageable);
		
		model.addAttribute("subjects", subjectPage); 
		model.addAttribute("maxPage", 5);
		
		return "subject/subjectList"; // 리턴할 파일 이름
	}
	
	//등록 화면 보여주기
	@GetMapping(value="/subject/regist")
	public String subjectRegist() {
		return "subject/subjectRegist";
	}
}
