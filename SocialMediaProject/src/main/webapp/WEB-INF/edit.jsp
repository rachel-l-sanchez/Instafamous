<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Post</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
		<jsp:include page="base.jsp" />

	<div class="d-flex justify-content-center">
		<form:form action="/update/post/${post.id}" class="row g-3 needs-validation d-inline-flex flex-column justify-content-center" method = "post" modelAttribute="post">
			<h2>Edit Post</h2>
			<div class="col">
				<input type="hidden" name = "_method" value="put">
				<form:input type="hidden" path="creator" value="${user.id}"/>
				<form:label class="col" path="caption">Caption</form:label>
				<form:input class="col" path="caption"/>
				<form:errors class="col" path="caption"></form:errors>
				<button class="btn btn-primary col-12 d-inline-flex justify-content-center">Submit</button>
			</div>
		</form:form>
	</div>
</body>
</html>