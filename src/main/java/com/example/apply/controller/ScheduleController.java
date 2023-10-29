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

import com.example.apply.dto.ScheduleDto;
import com.example.apply.entity.Member;
import com.example.apply.entity.Schedule;
import com.example.apply.service.MemberService;
import com.example.apply.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

	//화면 띄우기
	private final ScheduleService scheduleService;
	private final MemberService memberService;
	
	@GetMapping(value= {"/schedule/list","/schedule/list/{page}"})
	public String subjectList(Model model, ScheduleDto scheduleDto,@PathVariable("page") Optional<Integer> page,Principal principal) {
Member member = memberService.findMemberByEmail(principal.getName());
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
		Page<Schedule> scheduleList = scheduleService.getSchedulePage(pageable, member);
		
		model.addAttribute("scheduleList",scheduleList);
		model.addAttribute("headerBigMsg","SCHEDULE");
		model.addAttribute("imgurl","/img/TimeTable.jpg");
		model.addAttribute("headerMsg","수강신청 한 과목을 확인하세요.");

		return "schedule/scheduleList"; // 리턴할 파일 이름
	}

	/*
	 * // 게시글 리스트 보여주기 + 페이징
	 * 
	 * @GetMapping(value = { "/schedule/list", "/schedule/list/{page}" }) //
	 * Model:컨트롤러>뷰 데이터를 전달. 뷰 페이지와 폼 데이터 바인딩 public String list(Model model,
	 * ScheduleDto scheduleDto, @PathVariable("page") Optional<Integer> page) {
	 * 
	 * // 한 페이지당 5개씩 보여주기 Pageable pageable = PageRequest.of(page.isPresent()
	 * ?page.get() : 0, 5); Page<Schedule> list =
	 * scheduleService.getListPage(pageable);
	 * 
	 * // 숫자 5를 maxPage라는 이름으로 view에 보낸다 // model.addAttribute(String key, Object
	 * value); model.addAttribute("lists", list); model.addAttribute("maxPage", 5);
	 * 
	 * return "qna/qnaList"; }
	 */
}
