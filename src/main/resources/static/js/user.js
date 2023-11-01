let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
/*		$("#btn-login").on("click", () => {
			this.login();
		});*/
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
				alert("회원가입이 완료되었습니다.");
				//console.log(resp);
				location.href = "/";
			}).fail(function() {
				alert(JSON.stringify(error));
			}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	},
	
	update: function() {
		
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		//ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
				alert("회원수정이 완료되었습니다.");
				//console.log(resp);
				location.href = "/";
			}).fail(function() {
				alert(JSON.stringify(error));
			}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	},
	
/*		login: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}

		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
				alert("로그인이 완료되었습니다.");
				location.href = "/";
			}).fail(function() {
				alert(JSON.stringify(error));
			}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	}*/

};

index.init();