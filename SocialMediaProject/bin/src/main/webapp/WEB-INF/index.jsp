<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Registration Page</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="/css/main.css">
	
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<jsp:include page="base.jsp" />
		
		<div class="btn-group">
			<a href="/login" class="btn btn-primary">Login</a>
		</div>
	</nav>
    <p><form:errors path="user.*"/></p>
    
    <form:form method="POST" class = "form row d-flex flex-column" action="/register" modelAttribute="user">
       <h1>Register</h1> 
        <div class="col">
            <form:label for ="inputUsername4" class="form-label" path="name">Name:</form:label>
            <form:input class="form-control"  id ="inputName4" path="name"/>
        </div>
        <div class="col">
            <form:label for ="inputUsername4" class="form-label" path="username">Username:</form:label>
            <form:input class="form-control"  id ="inputUsername4" path="username"/>
        </div>
      	<div class="col">
            <form:label for="inputEmail4" class="form-label" path="email">Email</form:label>
            <form:input class="form-control" id="inputEmail4" path="email"/>
        </div>
      	<div class="col">
            <form:label  class="form-label" path="password">Password:</form:label>
            <form:password class="form-control" id="inputPassword4" path="password"/>
        </div>
      	<div class="col">
            <form:label  class="form-label" path="confirm">Password Confirmation:</form:label>
            <form:password class="form-control" id="inputPassword4" path="confirm"/>
        </div>
        <input type="submit" class = "btn btn-primary" value="Register"/>
    </form:form>
</body>
</html>