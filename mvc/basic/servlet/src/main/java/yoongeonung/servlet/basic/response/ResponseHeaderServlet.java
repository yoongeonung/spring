package yoongeonung.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // [status-line]
    // 상태코드 지정
    resp.setStatus(HttpServletResponse.SC_OK);

    // [response-headers]
    resp.setHeader("Content-Type", "text/plain;charset=utf-8");
    // cache 무효화
    resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    resp.setHeader("Pragma", "no-cache");
    // 커스텀 헤더
    resp.setHeader("my-header", "hello");

    //[Header 편의 메서드]

//    content(resp);
//    cookie(resp);
    redirect(resp);

    // [message body]
    PrintWriter writer = resp.getWriter();
    writer.println("ok");
  }

  private void redirect(HttpServletResponse resp) throws IOException {
    //Status Code 302
    //Location: /basic/hello-form.html
    //response.setStatus(HttpServletResponse.SC_FOUND); //302
    //response.setHeader("Location", "/basic/hello-form.html");
    resp.sendRedirect("/basic/hello-form.html");
  }

  private void cookie(HttpServletResponse resp) {
    /* Cookie
    Set-Cookie: myCookie=good; Max-Age=600;
    response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
    */
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600);
    resp.addCookie(cookie);
  }

  private void content(HttpServletResponse resp) {
    //Content-Type: text/plain;charset=utf-8
    //Content-Length: 2
    //response.setHeader("Content-Type", "text/plain;charset=utf-8");
    resp.setContentType("text/plain");
    resp.setCharacterEncoding("UTF-8");
    resp.setContentLength(2);
  }
}
