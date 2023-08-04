<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 7/27/2023
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <script src="https://kit.fontawesome.com/84744eb47a.js"></script>

    <link rel="stylesheet" type="text/css" href="asset/css/login.css">
</head>
<body>
    <div class="container">
        <div class="card">
            <c:if test="${requestScope.mess != null}">
                <div class="alert alert-primary" role="alert">
                        ${requestScope.mess}
                </div>
            </c:if>
            <div class="card-body">
                <h3>Member Login</h3>
                <form action="login" method="post" id="login-form">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fa-solid fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" id="inputEmail" placeholder="Username" name="Username"
                               aria-label="Username" aria-describedby="basic-addon1">
                        <span class="form-message"></span>
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fa-solid fa-user"></i></span>
                        </div>
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password"
                               name="password" rules="required min-8 max-50">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success form-control">Login</button>
                    </div>
                </form>
            </div>
            <div class="card-header">
                <a href="#">Forget Password ?</a>
            </div>
        </div>
    </div>
</body>
</html>
