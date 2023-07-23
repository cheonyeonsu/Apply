package com.example.apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Qna;

//extends받아야하니까 Qna(앤티티) 생성 > 클래스 이름이니까 대문자.
public interface QnaRepository extends JpaRepository<Qna,Long>{

	
}
