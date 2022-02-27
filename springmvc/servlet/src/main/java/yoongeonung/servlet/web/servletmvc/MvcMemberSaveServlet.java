package yoongeonung.servlet.web.servletmvc;
import yoongeonung.servlet.domain.member.Member;
import yoongeonung.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String VIEW_PATH = "/WEB-INF/views/save-result.jsp";

        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member();
        member.setUsername(username);
        member.setAge(age);
        memberRepository.save(member);

        req.setAttribute("member", member);

        RequestDispatcher dispatcher = req.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(req, resp);
    }
}
