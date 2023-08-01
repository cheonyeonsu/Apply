package com.example.apply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>, SubjectRepositoryCustom {
	 Page<SubjectDto> getSubjectPage(SubjectSearchDto subjectSearchDto,Pageable pageable);
  // Page<SubjectDto> subjectPage = subjectRepository.getSubjectPage(pageable); 
}
