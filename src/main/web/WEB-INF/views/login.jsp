<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login to Spring Phonebook</title>
</head>

<link rel="stylesheet" href="public/bootstrap/css/bootstrap-min.css" media="screen" type="text/css" />
<link rel="stylesheet" href="public/css/login.css" media="screen" type="text/css" />
<body>

<c:url var="authUrl" value="/static/j_spring_security_check" />

<div class="container">

  <form class="form-login" method="post" action="${authUrl}">
    <h2 class="form-login-heading">Please Login</h2>
    <p class = "lead error-message">${error}</p>
    <label for="input-username" class="sr-only">Login</label>
    <input type="login" id="input-username" name="j_username" class="form-control" placeholder="Login" required autofocus>
    <label for="input-password" class="sr-only">Password</label>
    <input type="password" id="input-password" name="j_password" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
  </form>
  <div class="row register-link">
    <a href="/register" >If you arn't an authorized user please sign up first</a>
  </div>
</div> <!-- /container -->


</body>
</html>
