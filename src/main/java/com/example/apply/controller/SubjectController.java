package com.example.apply.controller;

import java.security.Principal;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.Member;
import com.example.apply.entity.Schedule;
import com.example.apply.entity.Subject;
import com.example.apply.service.MemberService;
import com.example.apply.service.ScheduleService;
import com.example.apply.service.SubjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SubjectController {

	private final SubjectService subjectService;
	private final MemberService memberService;
	private final ScheduleService scheduleService;

	// 과목 리스트 보여주기, 페이지 매핑.
	@GetMapping(value = { "/subject/list", "/subject/list/{page}" })
	public String subjectList(Model model, SubjectSearchDto subjectSearchDto,
			@PathVariable("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
		Page<Subject> subjectPage = subjectService.getSubjectPage(subjectSearchDto, pageable);

		model.addAttribute("headerBigMsg", "SUBJECT");
		model.addAttribute("imgurl", "/img/Click.jpg"); /* 이미지 */
		model.addAttribute("headerMsg", "수강신청 페이지입니다.");
		model.addAttribute("subjects", subjectPage);
		model.addAttribute("maxPage", 5);

		return "subject/subjectList"; // 리턴할 파일 이름
	}

	// 과목 등록화면 띄우기
	@GetMapping(value = "/subject/regist")
	public String subjectRegist() {
		return "subject/subjectRegist";
	}

	// 과목 등록하기 : 과목 등록 요청이 오면 saveSubject 메서드를 호출하여 과목을 저장
	@PostMapping(value = "/subject/regist")
	public String addSubject(@Valid SubjectDto subjectDto, Principal principal) {

		// subjectDto.setSubjectStartDateStr(subjectDto.getSubjectStartDateStr().toString());
		// subjectDto.setSubjectEndDateStr(subjectDto.getSubjectEndDateStr().toString());

		try {
			subjectService.saveSubject(subjectDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/subject/list"; // 등록한 과목을 리스트 화면으로 돌아가 보여줌.
	}

	// 등록한 과목 보여주기
	@GetMapping(value = { "/subject/detail", "/subject/detail/{subjectId}" })
	public String subjectDetail(@PathVariable(name = "subjectId") Long subjectId, Model model, Principal principal) {
		Subject subject = subjectService.getDetailPage(subjectId);
		Member member = memberService.findMemberByEmail(principal.getName());

		model.addAttribute("member", member);
		model.addAttribute("subject", subject);

		return "subject/subjectDetail";
	}

	// 수정화면 보여주기
	@GetMapping(value = { "/subject/edit", "/subject/edit/{subjectId}" })
	public String subjectEidt(@PathVariable(name = "subjectId") Long subjectId, Model model) {
		Subject subject = subjectService.getDetailPage(subjectId);

		model.addAttribute("subject", subject);

		return "subject/subjectEdit";
	}

	// 수정 내용 저장하기
	@PostMapping(value = { "/subject/edit", "/subject/edit/{subjectId}" })
	public String subjectEidt(@Valid SubjectDto subjectDto, Principal principal,
			@PathVariable(name = "subjectId") Long subjectId) {
		subjectDto.setSubjectId(subjectId);

		subjectService.updateSubject(subjectId, subjectDto);

		return "redirect:/subject/detail/" + subjectId;
	}

	// 게시글 삭제하기
	@DeleteMapping("/subject/delete/{subjectId}")
	public @ResponseBody ResponseEntity deleteSubject(@PathVariable("subjectId") Long subjectId, Principal principal) {
		subjectService.deleteSubject(subjectId);

		return new ResponseEntity<Long>(subjectId, HttpStatus.OK);
	}

	// 수강신청하기
	@PostMapping(value = "/subject/insert")
	public @ResponseBody ResponseEntity subjectEidt(@RequestBody Map<String, Object> requestBody,Principal principal) {
		Long subjectId = Long.parseLong(requestBody.get("subjectId").toString());
		Member member = memberService.findMemberByEmail(principal.getName());
		Schedule resultSchedule = scheduleService.getMemberAndSubject(member, subjectId);
		
		if (resultSchedule != null) {
			return new ResponseEntity<String>("이미 등록한 과목입니다.", HttpStatus.BAD_REQUEST);
		}
		
		scheduleService.insertSchedule(member, subjectId);
		return new ResponseEntity<Long>(subjectId, HttpStatus.OK);

	}

}
