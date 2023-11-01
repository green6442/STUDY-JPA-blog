package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자 요청 -> HTML 파일 응답 = @Controller
//사용자 요청 -> Data 응답 = @RestController
@RestController 
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "subee", "1234", "subee@nate.com");
		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter : " + m.getId());
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 무조건 get 요청
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { // ?id=1&username=subee&password=1234&email=subee@nate.com 
		// Member m 으로 선언하면 하나하나 @RequestParam을 써주지 않아도 됨
		return "get 요청 : " + m.getId() + "," +m.getUsername() +"," + m.getPassword() +"," + m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // Body Data는 @RequestBody로 받음
		// MIME 타입 : text/plain, application/json
		// Text 형태로 JSON 형식을 보내면 Text 그대로 인식 -> 이 경우에는 String text 로 받아야함
		// JSON 형식으로 파싱하려면 Member m 으로 받아야 함
		return "post 요청 : " +  m.getId() + "," +m.getUsername() +"," + m.getPassword() +"," + m.getEmail();
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put") 
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + "," + m.getPassword();
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
