package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM : Java(다른 언어) Object 를 테이블로 만들어주는 기술
@Entity // User 클래스가 MySQL에 테이블로 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
// @DynamicInsert insert 할 때 null인 필드 제외
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략 따라감
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'") 기본값 설정
	@Enumerated(EnumType.STRING) // enum이 String임을 명시
	private RoleType role; // Enum
	
	private String oauth; // kakao, google
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
}
