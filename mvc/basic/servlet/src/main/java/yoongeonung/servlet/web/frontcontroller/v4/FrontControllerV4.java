package yoongeonung.servlet.web.frontcontroller.v4;

import static javax.servlet.http.HttpServletResponse.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.web.frontcontroller.MyView;
import yoongeonung.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import yoongeonung.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import yoongeonung.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {

  private final Map<String, ControllerV4> controllerMap = new HashMap<>();

  public FrontControllerV4() {
    controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
    controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String> paramMap = createParamMap(req);
    Map<String, Object> model = new HashMap<>();

    String uri = req.getRequestURI();
    ControllerV4 controller = controllerMap.get(uri);
    if (controller == null) {
      resp.setStatus(SC_NOT_FOUND);
      return;
    }

    MyView view = viewResolver(paramMap, model, controller);

    view.render(model,req, resp);
  }

  private MyView viewResolver(Map<String, String> paramMap, Map<String, Object> model,
      ControllerV4 controller) {
    String viewName = controller.process(paramMap, model);
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }

  private Map<String, String> createParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    req.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
    return paramMap;
  }
}
