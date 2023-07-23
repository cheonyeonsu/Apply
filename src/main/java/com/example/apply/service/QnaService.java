package com.example.apply.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.dto.QnaDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional //임포트 주의
public class QnaService {

	public Long saveQna(QnaDto qnaDto) {
		return null;
	}
	
	
}

