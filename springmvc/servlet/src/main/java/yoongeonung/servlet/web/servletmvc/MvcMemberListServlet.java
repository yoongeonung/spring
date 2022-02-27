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
import java.util.List;

@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();
        req.setAttribute("members", members);

        final String VIEW_PATH = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(req, resp);
    }
}
