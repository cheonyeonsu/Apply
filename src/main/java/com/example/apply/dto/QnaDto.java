package com.example.apply.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.example.apply.entity.Qna;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDto {

	private Long id;
	
	private String title;
	
	private LocalDateTime date;
	
	private String content;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	//dto를 entity로 바꿈.
	public Qna createQna() {
		return modelMapper.map(this,Qna.class); //모델매퍼에서 qnaclass가져온다. 
	}
}