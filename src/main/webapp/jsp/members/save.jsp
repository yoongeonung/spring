<%@ page import="jp.ac.hal.yoongeonung.springboot.domain.member.MemberRepository" %>
<%@ page import="jp.ac.hal.yoongeonung.springboot.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    JSP에서는 HttpServlet의 service()가 자동으로 호출된다고 보면된다
    HttpServlet의 request, response 파라미터도 import없이 그냥 사용 가능하다.
    Java로직 실행시에는 <% %> 안에서 실행가능. 접근제어자는 필요없다.
--%>

<%
    final MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>JSP - Save</title>
    <meta charset="UTF-8">
</head>
<body>
<ul>
    <li>ID : <%= member.getId() %></li>
    <li>USERNAME : <%= member.getUsername() %></li>
    <li>AGE : <%= member.getAge() %></li>
</ul>
</body>
</html>
