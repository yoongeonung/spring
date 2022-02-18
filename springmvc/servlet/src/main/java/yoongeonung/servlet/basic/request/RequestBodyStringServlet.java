package yoongeonung.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream(); // http message body의 내용을 바이트코드로 받을수 있다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// Spring이 제공하는 유틸리티

        System.out.println("messageBody = " + messageBody);

        resp.getWriter().write("OK");
    }
}
