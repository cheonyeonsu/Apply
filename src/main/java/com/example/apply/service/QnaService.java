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
	
	//의존성 주입
	private final QnaRepository qnaRepository;
	private final MemberRepository memberRepository;
	
	//게시글 저장 
	public Long saveQna(QnaDto qnaDto, String id) {
		Qna qna = qnaDto.createQna();
		
		Member member = memberRepository.findByUserId(id);
		qna.setMember(member); //조인컬럼 넣어줌
		qna.setDate(LocalDateTime.now()); //현재 시간 찍어줌.
		
		// 게시글 번호 1부터 시작
	    qna.setId(getNextQnaId());

		qnaRepository.save(qna);
		
		return qna.getId();
	}
	
	// 현재 게시글 중 가장 큰 id 값을 가져와서 1 증가시킨 값을 반환.
    private Long getNextQnaId() {
        Long maxId = qnaRepository.findMaxId(); // qnaRepository에서 가장 큰 id 값을 가져오는 쿼리 추가.
        return maxId != null ? maxId + 1 : 1L; // 첫 번째 게시글의 경우 maxId가 null일 수 있으므로 예외처리를 해줍니다.
    }
	
	@Transactional(readOnly = true) //데이터베이스 읽기 작업만 수행. 
	public Page<Qna> getListPage(Pageable pageable){
		Page<Qna> qnaPage = qnaRepository.findAll(pageable);
		return qnaPage;
		
	}
	
	public Qna getDetailPage(Long id) {
	
		Qna qna = qnaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		/*orElseThrow(EntityNotFoundException::new): 오류처리.
		 * Optional 객체가 비어있지 않을 경우, Optional이 감싸고 있는 값을 그대로 반환. 
		 * Optional 객체가 비어있을 경우,EntityNotFoundException (또는 원하는 다른 예외)를 생성해 Throw
		 */
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