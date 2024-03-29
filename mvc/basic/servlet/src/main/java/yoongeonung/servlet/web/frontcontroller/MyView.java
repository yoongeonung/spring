package yoongeonung.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {

  private final String viewPath;

  public MyView(String viewPath) {
    this.viewPath = viewPath;
  }

  public void render(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
    dispatcher.forward(req, resp);

  }

  public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    modelToRequestAttribute(model, req);

    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
    dispatcher.forward(req, resp);
  }

  private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
    model.forEach((name, o) -> req.setAttribute(name, o));
  }
}
