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
<title>Create Post</title>
<!-- bootstrap -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

<link rel="stylesheet" type="text/css" href="/css/main.css">

</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<h1>Create a Post for <c:out value="${currentUser.name}"/></h1>
		<jsp:include page="base.jsp" />
		
	</nav>
	<div class="d-flex justify-content-center">
		<form:form method = "post" class = "row g-3 needs-validation d-inline-flex flex-column justify-content-center" action = "/create/post" modelAttribute = "newPost" enctype = "multipart/form-data">
		<div class="col">
			<c:if test="${status.error}">
		      <c:forEach items="${status.errorMessages}" var="error">
		        <c:out value="${error}"/>
		      </c:forEach>
		    </c:if>
			<input type="hidden" name="user_id" value=${currentUser.id }"/>
			<div class="form-group col d-inline-flex justify-content-center">
			    <spring:bind path="imageURL">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:label class="form-label" path="imageURL">Upload Image</form:label>
						<span class="input-group-text">Upload an Image</span>
						<form:errors path = "imageURL"/>                                                
						<form:input class="form-control" type="file" path = "imageURL" cssClass = "form-control"/>
					</div>
				</spring:bind>
				
	      	</div>
			</div>
			<div class="col d-inline-flex justify-content-center">
				<spring:bind path="caption">
					<div class="form-group col-12 ${status.error ? 'has-error' : ''}">
				        <form:label class="form-label" path="caption">Caption</form:label>
				       	<form:errors path = "caption"/> 
				        <form:textarea type="text" class="form-control col-6" cols="35" path="caption"></form:textarea>
					</div>
				</spring:bind>
			</div>
			<button class ="btn btn-primary col-12 d-inline-flex justify-content-center">Create</button>
		</form:form>
	</div>
</body>
</html>