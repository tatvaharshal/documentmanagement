<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%@page session="true"%>
<html>
<head>
    <meta charset="utf-8">
    <%-- <link href="${pageContext.request.contextPath}/static/css/loginstyle.css" rel='stylesheet' type='text/css' /> --%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="${pageContext.request.contextPath}/resources/content/Document/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <style>
        .container{
            margin-top:20px;
            background-color: #EAE7E7;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 30px black;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 	<div class="generic-container" data-ng-controller="LovController as ctrl"> -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
                ${error}
        </div>
    </c:if>
    <c:if test="${not empty msg}">
         <div class="alert alert-success">
                 ${msg}
         </div>
    </c:if>
    <form action="./Login" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-3">User Name:</label>
            <div class="col-sm-9">
                <input type="text"  name="username" placeholder="" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3" >Password:</label>
            <div class="col-sm-9">
                <input type="password" name="password" placeholder="" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-primary" value="Login" id="login">Login</button>
                <button type="reset" class="btn btn-primary">Reset</button>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
    </form>
</div>
</body>