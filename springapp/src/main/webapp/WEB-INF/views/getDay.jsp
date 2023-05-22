<%--
  Created by IntelliJ IDEA.
  User: seokjun
  Date: 2023/05/22
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ page session="false" %>--%>

<html>
<head>
    <title>Get Day</title>
</head>
<body>
    <h1>year = ${myDate.year}</h1>
    <p>${myDate.year}년 ${myDate.month}월 ${myDate.date}일은 ${day}요일 입니다.</p>
</body>
</html>
