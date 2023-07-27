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
	
	//게시글 작성
	public Long saveQna(QnaDto qnaDto, String id) {
		Qna qna = qnaDto.createQna();
		
		Member member = memberRepository.findByUserId(id);
		qna.setMember(member); //조인컬럼 넣어줌
		qna.setDate(LocalDateTime.now()); //현재 시간 찍어줌.
		
		qnaRepository.save(qna);
		
		return qna.getId();
	}
	
	//작성한 글 리스트 보여주기
	@Transactional(readOnly = true)
	public Page<Qna> getListPage(Pageable pageable){
		Page<Qna> mainitemPage = qnaRepository.findAll(pageable);
		return mainitemPage;
		
	}
	
	//작성한 글 내용 보여주기
	public Qna getDetailPage(Long id) {
	
		Qna qna = qnaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		return qna;
		
	}
	
	//수정하기
	public Qna updateQna(Long id,QnaDto qnaDto) {
		
		Qna qna = qnaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		qna.updateQna(qnaDto.getTitle(), qnaDto.getContent());
		
		return qna;
	}
	
	//삭제하기
	public Qna deleteQna(Long id) {
		
		Qna qna = qnaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		qnaRepository.delete(qna);
		
		return qna;
	}
	
	
	
	
}