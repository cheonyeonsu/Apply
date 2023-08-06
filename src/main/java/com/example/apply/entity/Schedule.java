package com.example.apply.entity;

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

/*@Entity
@Table(name = "schedule")
@Getter
@Setter
@ToString*/
public class Schedule {

	/*
	@Id
	@Column(name = "schedule_id") 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long ScheduleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Long MemberId;
	 */
}
