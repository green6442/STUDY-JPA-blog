package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

// DAO 역할
// 자동으로 Bean 등록. 원래는 @Repository
public interface  BoardRepository extends JpaRepository<Board, Integer> {
// Board 테이블이 관리하는 Repository 이며, PK는 Integer
	
}
