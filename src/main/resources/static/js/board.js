let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});

		/*		$("#btn-login").on("click", () => {
					this.login();
				});*/
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function() {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	},

	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function() {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	},
	
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}

		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function() {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	},
	
	replySave: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};

		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`, // String 형태로 받음
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
			alert("댓글 작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function() {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	},
	
	replyDelete: function(boardId, replyId) {

		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`, // String 형태로 받음
			dataType: "json" // 응답의 문자열이 json 형태이면 js 오브젝트로 변경
		}).done(function(resp) {
			alert("댓글 삭제가 완료되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function() {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용하여 3개의 Data를 json으로 변경하여 insert 요청
	},

};

index.init();

