package yoongeonung.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import yoongeonung.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("Kakao");

        String value = objectMapper.writeValueAsString(helloData);
        resp.getOutputStream().print(value);
    }
}
