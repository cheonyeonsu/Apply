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
		
		return "schedule/scheduleList"; // 리턴할 파일 이름
	}
}
