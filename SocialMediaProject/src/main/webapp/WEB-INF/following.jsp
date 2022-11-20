<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Following</title>
<link rel="stylesheet" href="/css/main.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="base.jsp" />
<c:forEach var="follow" items = "${following}">
   <c:choose>
   		<c:when test="${follow.name == null}">    
   		</c:when>
		<c:otherwise>	
	<div class="d-inline-flex flex-row row justify-content-center">
		<div class="card col d-flex flex-row" style="width: 18rem;">
		  <img src="userImage.png"  class="card-img-top" alt="user icon">
		  	<div class="card-body">
			    <h5 class="card-title">
			    	<a class="p-1 rounded" href="/profile/${follow.id}"><c:out value="${follow.name }"/></a>
			    </h5>
			    <p class="card-text">
			    	<c:out value="${follow.username}"/>
				</p>
				<p class="card-text">
				 	<c:if test="${follow.followers.size() == 0}">
						<p class="card-text"><c:out value="${follow.followers.size()}"/></p>
					</c:if>
				</p>
			</div>
		</div> 
	</div>  
		</c:otherwise>
  </c:choose>
</c:forEach>
</body>
</html>