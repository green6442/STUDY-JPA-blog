package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
public class Board {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @Column(nullable = false, length = 100)
	 private String title;
	 
	 @Column(columnDefinition = "LONGTEXT")
	 @Lob // 대용량 데이터
	 private String content;
	 
	 private int count; // 조회수
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="userId")
	 private User user; // FK, 자바는 오브젝트 저장 가능
	 
	 @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // FK가 아니니 DB에 컬럼 만들지 마세요
	 // 한 페이지에 댓글창이 있을 경우 EAGER, 펼치기 버튼을 통해 다른 페이지에 댓글창이 있을 경우 LAZY
	 @JsonIgnoreProperties({"board"})
	 @OrderBy("id desc")
	 private List<Reply> replys;
	 
	 @CreationTimestamp // 시간 자동 입력
		private Timestamp createDate;
}
