package com.example.apply.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "qna")
@Getter
@Setter
@ToString //객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴.
public class Qna {

	@Id //기본키
	@Column(name = "qna_id") //db에 저장되는 이름이라 형식 다름.
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키 자동 생성
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private LocalDateTime date;
	
	@Column(nullable = false)
	private String content;
	
	//학생식별자
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; 
	
}
