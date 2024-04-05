<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-04-04
  Time: 오후 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <input type="text" name="tno" value="${dto.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${dto.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dto.dueDate}">
</div>
<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked": ""} readonly >
</div>
<div>

    <a href="/jdbcex/todo/modify?tno=${dto.tno}">수정/삭제하기</a>
    <a href="/jdbcex/todo/list">리스트로</a>
</div>
</body>
</html>
