package com.example.apply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.Subject;

public interface SubjectRepositoryCustom {
	Page<Subject> getSubjectPage(SubjectSearchDto subjectSearchDto, Pageable pageable);
}
