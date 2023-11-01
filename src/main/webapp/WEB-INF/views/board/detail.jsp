<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">BACK</button>
	<c:if test="${ board.user.id == principal.user.id}">
		<a href="/board/${board.id }/updateForm" class="btn btn-warning" id="btn-update">MODIFY</a>
		<button class="btn btn-danger" id="btn-delete">DELETE</button>
	</c:if>
	<br> <br>
	<div>
		글 번호 : <span id="id"><i>${board.id}</i></span> 작성자 : <span><i>${board.user.username}</i></span>
	</div>
	<br>
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div>
		<div>${board.content }</div>
	</div>
	<hr />

	<div class="card">
		<form>
		<input type="hidden" id="userId" value="${principal.user.id}">
			<input type="hidden" id="boardId" value="${board.id}">
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply-save" class="btn btn-primary">WRITE</button>
			</div>
		</form>
	</div>
	<br>
	<div class="card">
		<div class="card-header">REPLY LIST</div>
		<ul class="list-group" id="reply--box">
			<c:forEach var="reply" items="${board.replys}">
				<li class="list-group-item d-flex justify-content-between" id="reply--1">
					<div>${reply.content }</div>
					<div class="d-flex">
						<div class="font-italic">WRITER : ${reply.user.username }&nbsp;</div>
						<button class="badge" onClick="index.replyDelete(${board.id}, ${reply.id})">DELETE</button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>