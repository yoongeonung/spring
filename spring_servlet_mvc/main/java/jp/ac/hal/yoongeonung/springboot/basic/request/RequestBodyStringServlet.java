package jp.ac.hal.yoongeonung.springboot.basic.request;

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
        // HTTP Body에 있는 텍스트를 바이트코드로 가지고 온다.
        ServletInputStream inputStream = req.getInputStream();
        // Spring이 제공하는 유틸을 사용하여 바이트코드 -> 스트링으로 변환, 변환시 인코딩정보를 알려줘야한다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);


        resp.getWriter().write("OK");
    }
}
