<%@ page import="yoongeonung.servlet.domain.member.MemberRepository" %>
<%@ page import="yoongeonung.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yoongeonung
  Date: 2022/05/10
  Time: 6:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
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
    <%
        // JspWriter를 out으로사용 가능
        for (Member member : members) {
            out.write("<tr>");
            out.write("<td>" + member.getId() + "</td>");
            out.write("<td>" + member.getUsername() + "</td> ");
            out.write("<td>" + member.getAge() + "</td> ");
            out.write("</tr>");
        } %>
    </tbody>
</table>
</body>
</html>
