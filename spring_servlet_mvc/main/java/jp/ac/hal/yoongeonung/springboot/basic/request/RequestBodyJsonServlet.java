package jp.ac.hal.yoongeonung.springboot.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.hal.yoongeonung.springboot.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // spring boot 기본 jacson 라이브러리 사용
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // inputStream(bytecode) -> string(json도 결국엔 문자열)
        String messageBody = StreamUtils.copyToString(req.getInputStream(), StandardCharsets.UTF_8);
        // string -> json -> object
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        resp.getWriter().write("OK");
    }
}
