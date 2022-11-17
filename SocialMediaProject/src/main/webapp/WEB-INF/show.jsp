<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Show Post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/starter.css" />
    <link rel="stylesheet" href="/css/main.css"/>
    
  </head>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<div class="btn-group">
		  <a href="/dashboard/1" class="btn btn-primary active" aria-current="page">Active link</a>
		  <a href="/profile/${currentUser.id }" class="btn btn-primary">Your Profile</a>
		  <a href="/create" class="btn btn-primary">Upload a New Post</a>
		     <form id="logoutForm" method="POST" action="/logout">
		       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		       <input type="submit" class="btn btn-primary" value="Logout" />
		    </form>
		</div>
	</nav>
	<ul class="d-flex flex-column">
		<c:set var = "user" value = "${currentUser}"/>
		<c:choose>
		<c:when test="${followingUser.id.equals(currentPost.user_id)}">
			<h2><c:out value="${followingUser.name}"/>'s Post</h2>
			<c:out value = "${followingUser.name}"/>
		</c:when>
		<c:otherwise>
			<h2><c:out value="${currentUser.name}"/>'s Post</h2>
			<c:out value = "${currentUser.name}"/>
		</c:otherwise>
		</c:choose>
		<li class="btn_container d-flex flex-column"><img src="/${currentPost.imageURL }" width="240" height="300" alt="selected post"></li>
		<li class="d-flex flex-column"><c:out value="${currentPost.caption}"/></li>
		<li><form:form method = "POST" class="form" action = "/add/follower" modelAttribute = "newFollower">
			<div class="mb-3">
	            <form:input type="hidden" path="name" value="${followingUser.name}"/>
	        </div>
	        <div class="mb-3">
				<form:input type="hidden" path="username" value="${followingUser.username}"/>
	        </div>
	        <div class="mb-3">
	        	<form:input type="hidden" path="email" value="${followingUser.email}"/>
	      	</div>
	        <div class="mb-3">
	           	<form:input type="hidden" path="password" value="${followingUser.password}"/>
	        </div>
	        <div class="mb-3">
	       		<form:input type="hidden" path="confirm" value="${followingUser.confirm}"/>
	        </div>
			<button class ="btn btn-primary">Follow User</button></form:form><li>
	 			<form class="delete form h-25 d-inline-block d-flex w-75 p-3 flex-nowrap" action = "/unfollow/${currentUser.id}" method="POST">
	 				<input type="hidden" name="_method" value="delete"><button class="btn btn-primary">Unfollow User</button>
	 			</form>
        <li><a href="../add/like/${currentUser.id}/${currentPost.id}"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-hand-thumbs-up" viewBox="0 0 16 16">
  		<path d="M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2.144 2.144 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a9.84 9.84 0 0 0-.443.05 9.365 9.365 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111L8.864.046zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a8.908 8.908 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.224 2.224 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.866.866 0 0 1-.121.416c-.165.288-.503.56-1.066.56z"/>
		</svg></a></li>
		<c:forEach var="comment" items = "${allComments}">
		<li>
			<c:out value="${comment.username}"/>
			<c:out value="${comment.content}"/>
		</li>
		</c:forEach>
		<form:form method = "POST" class="form" action="/add/comment/${currentPost.id}" modelAttribute = "comment">
			<h2>Comment on this Post</h2>
			<input type="hidden" name="post_id" value="${currentPost.id}">
			<input type="hidden" name="user_id" value="${currentUser.id}">
			<input type="hidden" name="username" value="${currentUser.username }">
	        <div class="mb-3">	
	        	<form:label class="form-label" path="content">Content</form:label>
				<form:textarea path="content" class="form-control" cols="20" rows="2"></form:textarea>
			</div>
			<button class ="btn btn-primary">Add Comment to this Post</button>
		</form:form>
	</ul>
</body>
</html>