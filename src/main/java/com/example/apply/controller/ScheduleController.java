package com.example.apply.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.apply.dto.ScheduleDto;
import com.example.apply.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

	//화면 띄우기
	private final ScheduleService scheduleService;
	
	@GetMapping(value="/schedule/list")
	public String subjectList(Model model, ScheduleDto scheduleDto) {
		model.addAttribute("headerBigMsg","SCHEDULE");
		model.addAttribute("imgurl","/img/TimeTable.jpg");
		model.addAttribute("headerMsg","수강신청 한 과목을 확인하세요.");
		/*
		 * List<Subject> subjects = subjectService.getAllSubjects();
		 * model.addAttribute("subjects", subjects);
		 */
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
