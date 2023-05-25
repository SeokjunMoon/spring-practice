<%--
  Created by IntelliJ IDEA.
  User: seokjun
  Date: 2023/05/25
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Calc Day</title>
</head>
<body>
<h1>year=<%=request.getParameter("year") %></h1>
<P> ${myDate.year}년 ${myDate.month}월 ${myDate.date}일은 "${day}요일" 입니다.</P>

</body>
</html>
