<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2020/10/11
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAll">查询所有</a>

    <h3>测试save事务</h3>
    <form method="post" action="account/save">
        姓名:<input type="text" name="name">
        钱数：<input type="text" name="money">
        <input type="submit" value="提交">
    </form>
</body>
</html>
