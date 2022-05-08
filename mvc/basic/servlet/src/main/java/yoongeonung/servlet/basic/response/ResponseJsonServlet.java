package yoongeonung.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.basic.HelloData;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    //Content-Type: application/json
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    HelloData helloData = new HelloData();
    helloData.setUsername("Wooah");
    helloData.setAge(10);

//    resp.getWriter().write(objectMapper.writeValueAsString(helloData));
    resp.getOutputStream().print(objectMapper.writeValueAsString(helloData));
  }
}
