<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
3秒后跳转到主页面，若没有跳转请点击<a
        href="http://localhost:8080/index.jsp">这里</a> </h7>
<%
    response.setHeader("refresh", "http://localhost:8080/index.jsp");
%>
</body>
</html>