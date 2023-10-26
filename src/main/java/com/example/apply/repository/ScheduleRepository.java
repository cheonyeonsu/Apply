package com.example.apply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Member;
import com.example.apply.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	List<Schedule> findByMember(Member member);
}
