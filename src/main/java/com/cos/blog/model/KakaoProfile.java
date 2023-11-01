package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class KakaoProfile {

	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
	@JsonIgnore
	private String access_token;
	@JsonIgnore
	private String token_type;
	@JsonIgnore
	private String refresh_token;
	@JsonIgnore
	private String expires_in;
	@JsonIgnore
	private String profile_nickname;
	@JsonIgnore
	private String refresh_token_expires_in;
	@JsonIgnore
	private String scope;

	@Data
	class Properties {

		public String nickname;

	}

	@Data
	class KakaoAccount {

		public Boolean profile_nickname_needs_agreement;
		public Profile profile;

		@Data
		class Profile {

			public String nickname;

		}

	}

}