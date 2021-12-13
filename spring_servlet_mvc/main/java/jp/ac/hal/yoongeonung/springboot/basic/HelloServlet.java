package jp.ac.hal.yoongeonung.springboot.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * "/hello" 로 접속시 helloServlet이 호출되고, service 메서드가 실행된다.
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");

        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        // req 메시지 가져오기
        String name = req.getParameter("name");
        /**
         * resp객체는 응답이 나갈때
         * 응답메시지를 간단히 작성할수 있게 도와준다.
         */
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("hello " + name);
    }
}
