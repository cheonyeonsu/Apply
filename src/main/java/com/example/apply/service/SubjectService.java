package com.example.apply.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.dto.QnaDto;
import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.Member;
import com.example.apply.entity.Qna;
import com.example.apply.entity.Subject;
import com.example.apply.repository.SubjectRepository;
import com.example.apply.repository.SubjectRepositoryCustom;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService {

	private final SubjectRepository subjectRepository;

	// 과목 등록
	public Long saveSubject(SubjectDto subjectDto) {
		Subject subject = subjectDto.createSubject();	
		subjectRepository.save(subject);
		
		return subject.getSubjectId();
	}
	
	 @Transactional(readOnly = true) 
	 public Page<Subject> getSubjectPage(SubjectSearchDto subjectSearchDto, Pageable pageable){
	 Page<Subject> subjectPage = subjectRepository.getSubjectPage(subjectSearchDto,pageable); 
	 return  subjectPage;
	 }
	  
	 //등록한 과목 보여주기
	 public Subject getDetailPage(Long subjectId) {
			
		 Subject subject = subjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
			
			return subject;
			
		}
	 
	
}