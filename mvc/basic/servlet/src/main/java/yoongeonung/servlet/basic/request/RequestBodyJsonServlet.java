package yoongeonung.servlet.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import yoongeonung.servlet.basic.HelloData;

/**
 * http://localhost:8080/request-body-json
 * <p>
 * JSON 형식 전송 content-type: application/json message body: {"username": "hello", "age": 20} *
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String messageBody = StreamUtils.copyToString(req.getInputStream(), StandardCharsets.UTF_8);
    System.out.println("messageBody = " + messageBody);

    HelloData data = objectMapper.readValue(messageBody, HelloData.class);
    System.out.println("data = " + data);

    resp.getWriter().write("OK");
  }
}
