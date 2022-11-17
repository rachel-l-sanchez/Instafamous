<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
	 	<div class="btn-group d-flex justify-content-end">
    		<a href="/register" class="btn btn-primary active" aria-current="page">Register</a>
    	</div>
    </nav>
    <h1>Login</h1>
    <c:if test="${logoutMessage != null}">
        <c:out value="${logoutMessage}"></c:out>
    </c:if>
    <c:if test="${errorMessage != null}">
        <c:out value="${errorMessage}"></c:out>        
    </c:if>
    <form method="POST" "row g-3 d-flex justify-content-center" action="/login">
    	
	    <div class="col-md-6">
	            <label for="username" for="inputEmail4" class="form-label">Username</label>
	            <input type="text"  id="inputEmail4" id="username" class="form-control" name="username"/>
	     </div>
	     <div class="col-md-6">
	            <label for="password" for="inputPassword4 class="form-label">Password</label>
	            <input type="password"  id="inputPassword4" class="form-control" id="password" name="password"/>
	     </div>
	     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	     <button type="submit" class="btn btn-primary">Login</button>
    </form>
</body>
</html>