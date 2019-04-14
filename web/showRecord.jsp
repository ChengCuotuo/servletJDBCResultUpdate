<%--
  Created by IntelliJ IDEA.
  User: lei02
  Date: 2019/4/12
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="resultBean" class="mybean.data.Bean" scope="request" />
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
        body{
            color:#DDEE2f;
            font-size:20px;
        }
    </style>
</head>
<body>
<table border="1">
    <% String[] columnName = resultBean.getColumnName();
    %>
    <tr />
    <% String[][] record = resultBean.getTableRecord();
        for (int i = 0; i < record.length; i++) {
    %>  <tr />
    <%      for (int j = 0; j < record[i].length; j++){
    %>          <td><%= record[i][j] %></td>
    <%      }
    %>  <tr />
    <%  }
    %>
</table>
</body>

</html>
