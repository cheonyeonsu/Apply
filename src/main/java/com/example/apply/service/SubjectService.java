package com.example.apply.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.dto.QnaDto;
import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.Member;
import com.example.apply.repository.SubjectRepository;
import com.example.apply.repository.SubjectRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService {

	private final SubjectRepository subjectRepository;

	// 과목 저장
	public Long saveSubject(SubjectDto subjectDto, String id) {
		Subject subject = subjectDto.createSubject();	
		subjectRepository.save(subject);
		
		return subject.getId();
	}
	
	 @Transactional(readOnly = true) 
	 public Page<SubjectDto> getSubjectPage(SubjectSearchDto subjectSearchDto, Pageable pageable){
	 Page<SubjectDto> subjectPage = subjectRepository.getSubjectPage(subjectSearchDto,pageable); 
	 return  subjectPage;
	 }
	 
	 
	 
}