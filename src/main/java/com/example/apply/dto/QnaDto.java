package com.example.apply.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDto {
//erd 보고 작성
		
	private long id; //글번호
	
	private String title; //글 제목
	
	private LocalDateTime date; //작성일
	
	private String content; //글 내용
	
	
	
	
	
}
