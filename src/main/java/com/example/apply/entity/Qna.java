package com.example.apply.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // 엔티티 클래스로 정의. 꼭 있어야 함.
@Table(name = "qna") // 테이블 이름 지정.
@Getter
@Setter
@ToString //객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴.
public class Qna {
	
	@Id //기본키.
	@Column(name="qna_id") //db에 저장되는 이름이라 언더바 들어감
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임
	private Long id; //글 번호
	
	@Column(nullable = false)
	private String title; //글 제목
	
	@Column(nullable = false)
	private LocalDateTime date; //작성일
	
	@Column(nullable = false)
	private String content; //글 내용

	//학생식별자
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; 

	// Qna 엔티티 클래스에 포함되어 있음. 
	//주어진 title, content 값으로 외부에서(public) 글의 제목과 내용을 수정
	public void updateQna(String title, String content) {
		this.title = title;
		this.content = content;
	}

}