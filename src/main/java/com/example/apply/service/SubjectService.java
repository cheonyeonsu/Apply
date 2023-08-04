package com.example.apply.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.Qna;
import com.example.apply.entity.Subject;
import com.example.apply.repository.SubjectRepository;

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
	 
	//수정 내용 저장하기
	 public Subject updateSubject(Long subjectId,SubjectDto subjectDto) {
			
		 Subject subject = subjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
			
		 subject.updateSubject(subjectDto.getSubjectName(), subjectDto.getSubjectStartDate(),
				 subjectDto.getSubjectEndDate(),subjectDto.getSubjectDetail(),subjectDto.getSubjectTo());
			
			return subject;
		}
	 
	 //과목 삭제하기
	 public Subject deleteSubject(Long subjectId) {
			
		 Subject subject = subjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
			
		 subjectRepository.delete(subject);
			
			return subject;
		}
}