<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-04-04
  Time: 오전 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jdbcex/todo/register" method="post">
    <div>
        <input type="text" name="title" placeholder="INSERT TITLE">
    </div>
    <div>
        <input type="date" name="dueDate">
    </div>
    <div>
        <button type="submit">등록하기</button>
        <button type="reset">리셋</button>

    </div>
</form>
</body>
</html>
