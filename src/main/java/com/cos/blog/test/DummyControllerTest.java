package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

// html 파일이 아닌 data를 return 해주는 controller
@RestController
public class DummyControllerTest {
	
	@Autowired  // 의존성 주입 DI
	// DummyControllerTest 가 메모리에 뜰 때 UserRepository 도 같이 뜸
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public  String delete(@PathVariable int id) {

		if (userRepository.existsById(id)) {
	        userRepository.deleteById(id);
	        return "삭제 성공. id : " + id;
	    } else {
	        return "삭제 실패. 해당 id는 DB에 없습니다. id : " + id;
	    }

	}
	
	@Transactional // 함수 종료 시 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // @RequestBody : json 데이터 요청 시 Java Object 로 받아줌
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		// 람다식
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user);
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건의 데이터 return
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라미터 전달 받을 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		/* 데이터가 3개 밖에 없을 경우 user/3 을 하면 findById(id)를 통해 DB에서 SELECT 가능
		 * 그러나 user/4 를 하면 값이 없기 때문에 null 을 반환
		 * orElseGet을 통해 빈 user 객체를 넣어줌 → null 이 아님
		 * */
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 사용자가 없습니다. id : " + id );
			}
		});
		// 요청 : 웹브라우저 (html , js 만 인식)
		// user 객체 = 자바 오브젝트 이기 때문에 웹브라우저가 이해할 수 있는 데이터로 변환해야함 →  json
		return user;
	}
	
	// http 요청 시 body 에 username, password, email 데이터 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
		// String username, String password, String email
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}
