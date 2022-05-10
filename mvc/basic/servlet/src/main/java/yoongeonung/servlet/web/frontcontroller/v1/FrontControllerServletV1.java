package yoongeonung.servlet.web.frontcontroller.v1;

import static javax.servlet.http.HttpServletResponse.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import yoongeonung.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import yoongeonung.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

  private final Map<String, ControllerV1> controllerMap = new HashMap<>();

  public FrontControllerServletV1() {
    controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
    controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
    controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String uri = req.getRequestURI();
    ControllerV1 controllerV1 = controllerMap.get(uri);
    if (controllerV1 == null) {
      resp.setStatus(SC_NOT_FOUND);
      return;
    }

    controllerV1.process(req, resp);

  }
}
