<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Post</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<h1>Create a Post for <c:out value="${currentUser.name}"/></h1>
		<jsp:include page="base.jsp" />
		
	</nav>
	<form:form method = "post" class = "row g-3 needs-validation d-flex flex-column" action = "/create/post" modelAttribute = "newPost" enctype = "multipart/form-data" novalidate ="">
		<div class="col-md-6">
			<div class="form-group ">
				<form:label class="form-label" path="imageURL">Upload Image</form:label>
				<div class="form-group">
					<span class="input-group-text">Upload an Image</span>
					<form:input class="form-control" type="file" path = "imageURL" cssClass = "form-control"/>
				</div>
	      	</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
		        <form:label class="form-label" path="caption">Caption</form:label>
		        <form:textarea type="text" class="form-control" path="caption"></form:textarea>
			  <div class="invalid-feedback">Please include a caption.</div>
			</div>
		</div>
		<button class ="btn btn-primary">Create</button>
	</form:form>
</body>
</html>