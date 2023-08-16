package com.example.apply.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

	/*
	 * //페이징
	 * 
	 * @Transactional(readOnly = true) //데이터베이스 읽기 작업만 수행. public Page<Schedule>
	 * getListPage(Pageable pageable){ Page<Schedule> qnaPage =
	 * scheduleRepository.findAll(pageable); return schedulePage;
	 * 
	 * }
	 */
}
