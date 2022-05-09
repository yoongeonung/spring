<%@ page import="yoongeonung.servlet.domain.member.MemberRepository" %>
<%@ page import="yoongeonung.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: yoongeonung
  Date: 2022/05/10
  Time: 6:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    // request, response parameter는 언제나 사용 가능하다.
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Save</title>
</head>
<body>
성공
<ul>
    <li>
        id=<%=member.getId()%>
    </li>
    <li>
        username=<%=member.getUsername()%>
    </li>
    <li>
        age=<%=member.getAge()%>
    </li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
