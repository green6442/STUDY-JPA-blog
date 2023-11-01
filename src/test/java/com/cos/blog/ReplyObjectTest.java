package com.cos.blog;

import org.junit.platform.commons.annotation.Testable;

import com.cos.blog.model.Reply;

@Testable
public class ReplyObjectTest {

	public void 투스트링테스트() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("안녕")
				.build();
		
		System.out.println(reply); // 오브젝트 출력 시에 toString 자동 호출
	}
}
