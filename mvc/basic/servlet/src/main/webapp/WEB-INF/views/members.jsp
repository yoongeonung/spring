<%--
  Created by IntelliJ IDEA.
  User: yoongeonung
  Date: 2022/05/10
  Time: 7:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Members</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
        <tr>
            <c:forEach var="member" items="${members}" >
                <td>${member.id}</td>
                <td>${member.username}</td>
                <td>${member.age}</td>
            </c:forEach>
        </tr>
    </tbody>
</table>
</body>
</html>
