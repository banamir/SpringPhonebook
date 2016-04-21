<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qween
  Date: 18.04.16
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration of user</title>
</head>

<link rel="stylesheet" href="public/bootstrap/css/bootstrap-min.css" media="screen" type="text/css" />
<link rel="stylesheet" href="public/css/login.css" media="screen" type="text/css" />

<body>

<c:if test=""></c:if>

<div class="container">
  <form class="form-login" modelAttribute="PhonebookUser" method="post" action="${authUrl}">
    <h2 class="form-login-heading">Registration</h2>
    <p class = "lead error-message">${error}</p>
    <label for="input-username" class="sr-only">Username</label>
    <input type="login" id="input-username" name="username" class="form-control" placeholder="Username" value="${(user != null)? user.username: ""}" required autofocus>
    <label for="input-fullName" class="sr-only"></label>
    <input type="fullname" id="input-fullName" name="fullName" class="form-control" placeholder="Full Name" value="${(user != null)? user.fullName: ""}" required autofocus>
    <label for="input-password" class="sr-only">Password</label>
    <input type="password" id="input-password" name="password" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
  </form>
</div> <!-- /container -->

</body>
</html>
