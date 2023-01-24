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
	<nav class="navbar navbar-dark bg-dark d-flex justify-content-center row">
		<h1 class="text-white">Welcome <c:out value="${currentUser.name}"/></h1>
    	<h2>Newsfeed</h2>
    	<div class="btn-group bg-light row-col-12">
			<a href="/dashboard/1" class="btn btn-light active" aria-current="page">Newsfeed</a>
			<a href="/settings/${currentUser.id}" class="btn btn-light">Reset Password</a>
			<a href="/profile/${currentUser.id }" class="btn btn-light">Your Profile</a>
			<a href="/create" class="btn btn-light">Upload a New Post</a>
			<a href="/edit/1" class="btn btn-light active">Edit Post</a>
			<a href="../my/followers" class="btn btn-light">See Your Followers</a>
			<a href="/following" class="btn btn-light">Following</a>
			<a class="btn btn-light row-col-12"><form id="logoutForm" method="POST" action="/logout">
			     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			     <input type="submit" class="btn btn-light" value="Logout" />
			</form></a>
		</div>
	</nav>
</body>
</html>