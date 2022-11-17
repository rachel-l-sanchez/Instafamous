<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Settings</title>
</head>
<body>
	<jsp:include page="base.jsp" />
	<form:form method="POST" class = "row g-3" action="/update/settings/${currentUser.id}" modelAttribute="user">
       <h1>Update your Account</h1> 
       	<input type="hidden" name="_method" value="put">
       <div class="col-md-6">
            <form:label class="form-label"  for="inputName4"  path="name">Name:</form:label>
            <form:errors path = "name"/>
            <form:input class="form-control" id="inputName4"  path="name"/>
        </div>
       <div class="col-md-6">
            <form:label  class="form-label" for="inputUsername4" path="username">Username:</form:label>
            <form:errors path="username"/>
            <form:input class="form-control" id="inputUsername4" path="username"/>
        </div>
      	<div class="col-md-6">
            <form:label  for="inputEmail4" class="form-label"  path="email">Email</form:label>
           	<form:errors path = "email"/>
            <form:input class="form-control" id="inputEmail4" path="email"/>
        </div>
      	<div class="col-md-6">
            <form:label for="inputPassword4" class="form-label" id="inputPassword4" path="password">Password:</form:label>
           	<form:errors path = "password"/>
            <form:password class="form-control" id="inputPassword4" path="password"/>
        </div>
        <input type="submit" class = "btn btn-primary" value="Register"/>
    </form:form>
</body>
</html>