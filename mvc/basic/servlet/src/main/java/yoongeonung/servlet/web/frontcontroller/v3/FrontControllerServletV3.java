package yoongeonung.servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.web.frontcontroller.ModelView;
import yoongeonung.servlet.web.frontcontroller.MyView;
import yoongeonung.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import yoongeonung.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import yoongeonung.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

  private final Map<String, ControllerV3> controllerMap = new HashMap<>();

  public FrontControllerServletV3() {
    controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String> paramMap = createParamMap(req);

    String uri = req.getRequestURI();
    ControllerV3 controller = controllerMap.get(uri);

    ModelView modelView = controller.process(paramMap);

    MyView view = viewResolver(modelView);

    view.render(modelView.getModel(),req, resp);
  }

  private Map<String, String> createParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    req.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
    return paramMap;
  }

  private MyView viewResolver(ModelView modelView) {
    String viewName = modelView.getViewName();
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }
}
