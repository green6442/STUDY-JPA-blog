package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장해준다.
@Getter
public class PrincipalDetail implements UserDetails {
	
	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료 여부 (true: 만료 안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 여부 (true : 잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 만료 여부 (true : 만료되지 않음)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정 활성화 여부 (true: 활성화)
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정이 가지고 있는 권한 목록 리턴
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(()->{return "ROLE_" + user.getRole(); });
		
		return authorities ;
	}
	
}
