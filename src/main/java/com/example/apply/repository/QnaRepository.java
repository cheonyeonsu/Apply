package com.example.apply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.apply.dto.QnaDto;
import com.example.apply.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna, Long>{

	Page<Qna> findAll(Pageable pageable);
	
}
