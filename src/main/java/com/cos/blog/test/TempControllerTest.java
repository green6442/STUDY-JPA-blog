package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일 리턴 기본 경로 : src/main/resources/static
		// return 명 : /home.html 로 해야함
		// full 경로 : src/main/resources/static/home.html 로 찾게 됨
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix : /WEB-INF/views/
		// sffix : .jsp
		// full 경로 : /WEB-INF/views/test.jsp
		return "test";
	}
}