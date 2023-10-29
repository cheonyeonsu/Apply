package com.example.apply.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Member;
import com.example.apply.entity.Schedule;
import com.example.apply.entity.Subject;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	Page<Schedule> findByMember(Member member, Pageable pageable);
	
	Schedule findByMemberAndSubject(Member member, Subject subject);
}
