<%@ page contentType="text/html" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%

%>
<html>
<head>
    <title>JSP - Form</title>
    <meta charset="UTF-8">
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
