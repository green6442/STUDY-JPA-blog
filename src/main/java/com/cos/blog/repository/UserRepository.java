package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO 역할
// 자동으로 Bean 등록. 원래는 @Repository
public interface  UserRepository extends JpaRepository<User, Integer> {
// User 테이블이 관리하는 Repository 이며, PK는 Integer
	
	//SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
	
	// JPA Naming 전략
	// select * from user where username=? and password = ?;
	/* User findByUsernameAndPassword(String username, String password); */
	
	/*
	 * @Query(value=" select * from user where username=?1 and password = ?2;",
	 * nativeQuery = true) User login(String username, String password);
	 */
}
