package com.example.apply.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.entity.Member;
import com.example.apply.entity.Schedule;
import com.example.apply.entity.Subject;
import com.example.apply.repository.MemberRepository;
import com.example.apply.repository.ScheduleRepository;
import com.example.apply.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
	
	private final MemberRepository memberRepository;
	private final SubjectRepository subjectRepository;
	private final ScheduleRepository scheduleRepository;

	/*
	 * //페이징
	 * 
	 * @Transactional(readOnly = true) //데이터베이스 읽기 작업만 수행. public Page<Schedule>
	 * getListPage(Pageable pageable){ Page<Schedule> qnaPage =
	 * scheduleRepository.findAll(pageable); return schedulePage;
	 * 
	 * }
	 */
	
	public void insertSchedule(Member member, Long SubjectId) {
		Subject subject = subjectRepository.findById(SubjectId).orElseThrow();	
		
		Schedule schedule = new Schedule();
		
		schedule.setMember(member);
		schedule.setSubject(subject);
		
		scheduleRepository.save(schedule);
		}
	
	public Page<Schedule> getSchedulePage(Pageable pageable, Member member) {
		
		return scheduleRepository.findByMember(member, pageable);
	}
	
	public Schedule getMemberAndSubject(Member member, Long subjectId) {
		Subject subject = subjectRepository.findById(subjectId).orElseThrow();
		
		return scheduleRepository.findByMemberAndSubject(member,subject);
	}
}
