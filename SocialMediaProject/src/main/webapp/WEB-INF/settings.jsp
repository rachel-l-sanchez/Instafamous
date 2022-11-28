<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Reset</title>
	<link rel="stylesheet" type="text/css" href="/css/main.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="base.jsp" />
	<form:form method="POST" class = "row d-flex flex-column g-3 bg-primary p-2" style="--bs-bg-opacity: .5;" action="/reset-password" modelAttribute="user">
       <h1>Update your Account</h1> 
       	<input type="hidden" name="_method" value="put">
       	<input type="hidden" name="id" value="${currentUser.id }">
	       <div class="col">
	            <form:label for="inputPassword4" class="form-label" id="inputPassword4" path="password">Password:</form:label>
	           	<form:errors path = "password"/>
	            <form:password class="form-control" id="inputPassword4" path="password"/>
        	</div>
        <input type="submit" class = "btn btn-primary" value="Register"/>
    </form:form>
</body>
</html>