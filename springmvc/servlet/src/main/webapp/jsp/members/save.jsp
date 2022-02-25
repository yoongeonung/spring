<%@ page import="yoongeonung.servlet.domain.member.Member" %>
<%@ page import="yoongeonung.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response, out는 그냥 사용가능

    final MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member();
    member.setUsername(username);
    member.setAge(age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공!
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age = <%=member.getAge()%></li>
</ul>
</body>
</html>
