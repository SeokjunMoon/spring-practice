<%--
  Created by IntelliJ IDEA.
  User: seokjun
  Date: 2023/05/26
  Time: 7:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%--    바인딩된 user 객체의 정보 출력--%>
    <h1>id = ${user.id}</h1>
    <h1>pwd = ${user.pwd}</h1>
    <h1>name = ${user.name}</h1>
    <h1>email = ${user.email}</h1>
    <h1>birth = ${user.birth}</h1>
    <h1>sns = ${user.sns}</h1>
    <h1>hobby = ${user.hobby}</h1>
</body>
</html>