<%--
  Created by IntelliJ IDEA.
  User: seokjun
  Date: 2023/05/25
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>spring-basic</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>
<body>
    <div id="menu">
        <ul>
            <li id="logo">spring-basic</li>
            <li><a href="<c:url value='/'/>">Home</a></li>
            <li><a href="<c:url value='/board/list'/>">Board</a></li>
            <li><a href="<c:url value='/login/login'/>">login</a></li>
            <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
            <li><a href=""><i class="fas fa-search small"></i></a></li>
        </ul>
    </div>
    <div style="text-align:center">
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
    </div>
</body>
</html>