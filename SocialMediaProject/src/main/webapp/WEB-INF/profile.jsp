<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css"/> 
  </head>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<jsp:include page="base.jsp" />

		<h1><c:set var = "user" value = "${oneUser}"/>
		<c:choose>
		<c:when test="${user.id.equals(onePost.user_id)}">
		<c:out value = "${oneUser.name}"/>
		</c:when>
		<c:otherwise>
		<c:out value = "${currentUser.name}"/>
		</c:otherwise>
		</c:choose>'s Profile</h1>
	</nav>
	<main>
		<c:forEach begin="1" end="${totalPages}" var="index">
			<div class="row">
  				<div class="col-4">
    				<div id="list-example" class="list-group">
						<a class="list-group-item list-group-item-action" href="/dashboard/${index}">${index}</a>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:forEach var="post" items = "${oneUser.createdPosts}" varStatus="index">
			<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false">
			  <div class="carousel-indicators">
			    <button type="button" data-bs-target="#See Post 1" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			    <button type="button" data-bs-target="#See Post 2" data-bs-slide-to="1" aria-label="Slide 2"></button>
			    <button type="button" data-bs-target="#See Post 3" data-bs-slide-to="2" aria-label="Slide 3"></button>
			  </div>
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			    	<a href="/detail/${post.id}"><img class="d-block w-100" src="/${post.imageURL[index.count]}" width="240" height="300"/></a>
				      <div class="carousel-caption d-none d-md-block">
				        <p><c:out value="${post.caption[index.count]}"/></p>
				      </div>
			    </div>
			    <div class="carousel-item">
			      <a href="/detail/${post.id}"><img class="d-block w-100" src="/${post.imageURL[index.count]}" width="240" height="300"/></a>
			      <div class="carousel-caption d-none d-md-block">
			        <p><c:out value="${post.caption[index.count] }"/></p>
			      </div>
			    </div>
			    <div class="carousel-item">
			      <a href="/detail/${post.id}"><img class="d-block w-100" src="/${post.imageURL[index.count]}" width="240" height="300"/></a>
			      <div class="carousel-caption d-none d-md-block">
			        <p><c:out value="${post.caption[index.count] }"/></p>
			      </div>
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
		</c:forEach>
	</main>
</body>
</html>