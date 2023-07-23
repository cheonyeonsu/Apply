package com.example.apply.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDto {

	private Long id;
	
	private String title;
	
	private LocalDateTime date;
	
	private String content;
	
	
}
