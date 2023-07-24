package com.example.apply.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.apply.dto.QnaDto;
import com.example.apply.entity.Member;
import com.example.apply.entity.Qna;
import com.example.apply.repository.MemberRepository;
import com.example.apply.repository.QnaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QnaService {

	private final QnaRepository qnaRepository;
	private final MemberRepository memberRepository;
	
	public Long saveQna(QnaDto qnaDto, String id) {
		
		
		Qna qna = qnaDto.createQna();
		
		Member member = memberRepository.findByUserId(id);
		qna.setMember(member);//조인컬럼 넣어주기.	
		qna.setDate(LocalDateTime.now()); //현재날짜.
		
		qnaRepository.save(qna);
		
		
		return qna.getId();
	}
}
