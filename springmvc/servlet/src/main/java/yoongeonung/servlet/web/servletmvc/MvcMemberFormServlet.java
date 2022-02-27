package yoongeonung.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String VIEW_PATH = "/WEB-INF/views/new-form.jsp";
        // 컨트롤러에서 뷰로 넘아갈때 사용, 패스지정 필수, 서버내부에서 호출한다.
        // dispatcher : (열차·버스·비행기 등이 정시 출발하도록 관리하는) 운행 관리원[조차원]
        RequestDispatcher dispatcher = req.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(req, resp);
    }
}
