package com.example.apply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.apply.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna,Long>{

	//extends받아야하니까 Qna 생성. ★클래스 이름은 대문자 기입★ 
	Page<Qna> findAll(Pageable pageable);
	
	// Qna 테이블에서 가장 큰 id 값을 가져오는 쿼리를 작성
    @Query("SELECT MAX(q.id) FROM Qna q")
    Long findMaxId();
}