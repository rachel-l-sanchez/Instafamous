<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/css/main.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<h1>Welcome <c:out value="${currentUser.name}"/></h1>
    	<h2>Newsfeed</h2>
    	<div class="btn-group">
			<a href="/dashboard/${pages}" class="btn btn-primary active" aria-current="page">Newsfeed</a>
			<a href="/settings/${currentUser.id}" class="btn btn-primary">Update Your Account</a>
			<a href="/profile/${currentUser.id }" class="btn btn-primary">Your Profile</a>
			<a href="/create" class="btn btn-primary">Upload a New Post</a>
			<form id="logoutForm" method="POST" action="/logout">
			     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			     <input type="submit" class="btn btn-primary" value="Logout" />
			</form>
		</div>
	</nav>
</body>
</html>