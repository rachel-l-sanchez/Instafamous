<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Followers</title>
<link rel="stylesheet" href="/css/main.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="base.jsp" />
<c:forEach var="follow" items = "${followers}">
<div class="row">
  <div class="col">
    <div id="simple-list-example" class="d-flex flex-column gap-2 simple-list-example-scrollspy text-center">
      <a class="p-1 rounded" href="/profile/${follow.id}"><c:out value="${follow.name }"/></a>
    </div>
  </div>
  <div class="col">
    <div data-bs-spy="scroll" data-bs-target="#simple-list-example" data-bs-offset="0" data-bs-smooth-scroll="true" class="scrollspy-example" tabindex="0">
      <h4 id="simple-list-item-1"><c:out value="${follow.username}"/></h4>
      	<c:if test="${follow.following.size() == 0}">
			<p class="card-text"><c:out value="${follow.following.size()}"/></p>
		</c:if>
    </div>
  </div>
 </div>
</c:forEach>
</body>
</html>