<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-04-03
  Time: 오후 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>나의할일 list</h1><br>
<h3>${loginInfo.mid} 님이 접속중입니다.</h3>

<ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <span><a href="/jdbcex/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "DONE": "NOT YET"}</span>
        </li>
    </c:forEach>
</ul>

<form action="/jdbcex/logout" method="post">
    <button>로그아웃</button>
</form>


</body>
</html>
