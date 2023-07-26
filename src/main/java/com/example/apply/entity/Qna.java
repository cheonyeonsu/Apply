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

@Entity
@Table(name = "qna")
<<<<<<< HEAD
@Getter
@Setter
@ToString
=======
@ToString
@Getter
@Setter
>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
public class Qna {

	@Id
	@Column(name = "qna_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private LocalDateTime date;
	
	@Column(nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
<<<<<<< HEAD
=======
	
	public void updateQna(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
}
