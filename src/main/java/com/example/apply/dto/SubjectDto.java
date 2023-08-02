package com.example.apply.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;

import com.example.apply.entity.Subject;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {

	private Long subjectId;

	// 과목명
	private String subjectName;

	// 과목 설명
	private String subjectDetail;

	// 수강 가능 인원
	private int subjectTo;
	
	//개강일자
	private String subjectStartDateStr;
    
	//종강일자
	private String subjectEndDateStr;

	
    public LocalDateTime getSubjectStartDate() {
        return LocalDateTime.parse(subjectStartDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

    public LocalDateTime getSubjectEndDate() {
        return LocalDateTime.parse(subjectEndDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }
    
	@QueryProjection
	public SubjectDto(Long subjectId, String subjectName,
			String subjectDetail,int subjectTo, String subjectStartDateStr, String subjectEndDateStr) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectStartDateStr = subjectStartDateStr;
		this.subjectEndDateStr= subjectEndDateStr;
		this.subjectDetail=subjectDetail;
		this.subjectTo = subjectTo;
		
	}
	
	//DTO와 Entity간 매핑을 수행하기 위한 객체를 선언, 초기화.
	private static ModelMapper modelMapper = new ModelMapper();
	
	
	 //dto>entity 변환
	public Subject createSubject() { 
		Subject subject = new Subject();
 	
		subject.setSubjectName(this.subjectName);
		subject.setSubjectStartDate(this.getSubjectStartDate());
		subject.setSubjectEndDate(this.getSubjectEndDate());;
		subject.setSubjectDetail(this.subjectDetail);
		subject.setSubjectTo(this.subjectTo);
		
		return subject;
	}
}