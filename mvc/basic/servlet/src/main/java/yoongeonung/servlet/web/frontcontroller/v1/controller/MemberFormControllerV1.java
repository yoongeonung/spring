package yoongeonung.servlet.web.frontcontroller.v1.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.web.frontcontroller.v1.ControllerV1;

public class MemberFormControllerV1 implements ControllerV1 {

  @Override
  public void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/new-form.jsp");
    dispatcher.forward(req, resp);
  }
}
