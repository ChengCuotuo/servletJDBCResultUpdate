<%--
  Created by IntelliJ IDEA.
  User: lei02
  Date: 2019/4/14
  Time: 7:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>insert info</title>
    <style type="text/css">
      body{
        color:#AAFFEE;
        font-size:16px;
      }
      input{
        size:20px;
      }
    </style>
  </head>
  <body>
    <form action="insertServlet?dataBase=chapter11&tableName=produce" method="post">
      <b>添加新记录</b><br />
      产品号：<input type="text" name="number"/>
      <br/>名称：<input type="text" name="name" />
      <br/>生产日期（日期必须使用 - 或者 / 格式）
      <br/><input type="text" name="mdadetime"/>
      <br/>价格：<input type="text" name="price" />
      <br /><input type="submit" name="b" value="提交"/>
    </form>
  </body>
</html>
