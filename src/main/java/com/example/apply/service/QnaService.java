package com.example.apply.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.dto.QnaDto;
import com.example.apply.entity.Member;
import com.example.apply.entity.Qna;
import com.example.apply.repository.MemberRepository;
import com.example.apply.repository.QnaRepository;

import jakarta.persistence.EntityNotFoundException;
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
		qna.setMember(member); //조인컬럼 넣어줌
		qna.setDate(LocalDateTime.now()); //현재 시간 찍어줌.
		
		qnaRepository.save(qna);
		
		return qna.getId();
	}
	
	@Transactional(readOnly = true)
	public Page<Qna> getListPage(Pageable pageable){
		Page<Qna> mainitemPage = qnaRepository.findAll(pageable);
		return mainitemPage;
		
	}
	
	public Qna getDetailPage(Long id) {
		
		// 1. qna객체에 담아서 리턴시키던가
		// 2. findById를 호출한 자체를 리턴시키던가. 
		
		Qna qna = qnaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		
		return qna;
		
	}
	
	public Qna updateQna(Long id,QnaDto qnaDto) {
		
		Qna qna = qnaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		qna.updateQna(qnaDto.getTitle(), qnaDto.getContent());
		
		return qna;
	}
	
	public Qna deleteQna(Long id) {
		
		Qna qna = qnaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		qnaRepository.delete(qna);
		
		return qna;
	}
	
	
	
	
}
