<%--
  Created by IntelliJ IDEA.
  User: HUANG
  Date: 2020/10/4
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>文件上传</h3>

    <form action="user/fileupload1" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="upload"><br>
        <input type="submit" value="上传">
    </form>

    <h3>springmvc文件上传</h3>
    <form action="user/fileupload2" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="upload"><br>
        <input type="submit" value="上传">
    </form>

    <h3>springmvc跨服务器文件上传</h3>
    <form action="user/fileupload3" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="upload"><br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
