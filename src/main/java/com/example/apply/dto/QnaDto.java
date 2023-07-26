package com.example.apply.dto;

import java.time.LocalDateTime;

<<<<<<< HEAD
=======
import org.modelmapper.ModelMapper;

import com.example.apply.entity.Qna;

>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDto {
<<<<<<< HEAD
	
=======

>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
	private Long id;
	
	private String title;
	
	private LocalDateTime date;
	
	private String content;
<<<<<<< HEAD
=======
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Qna createQna() {
		return modelMapper.map(this, Qna.class);
	}
	
>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
}
