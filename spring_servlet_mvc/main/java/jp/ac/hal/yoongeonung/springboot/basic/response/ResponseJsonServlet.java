package jp.ac.hal.yoongeonung.springboot.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.hal.yoongeonung.springboot.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("Line");

        ObjectMapper objectMapper = new ObjectMapper();
        // Object -> String
        String helloDataStr = objectMapper.writeValueAsString(helloData);
        System.out.println("helloDataStr = " + helloDataStr);


        PrintWriter writer = resp.getWriter();
        writer.write(helloDataStr);
    }
}
