package yoongeonung.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    //Content-Type: text/html;charset=utf-8
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");

    // HTML코드를 작성
    PrintWriter writer = resp.getWriter();
    writer.write("<html>");
    writer.write("<body>");
    writer.write("<h1>");
    writer.write("KAKAO");
    writer.write("</h1>");
    writer.write("</body>");
    writer.write("</html>");
  }
}
