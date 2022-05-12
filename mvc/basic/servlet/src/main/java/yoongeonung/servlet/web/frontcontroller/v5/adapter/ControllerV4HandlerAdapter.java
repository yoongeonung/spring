package yoongeonung.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.web.frontcontroller.ModelView;
import yoongeonung.servlet.web.frontcontroller.v4.ControllerV4;
import yoongeonung.servlet.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return handler instanceof ControllerV4;
  }

  @Override
  public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException {

    Map<String, Object> model = new HashMap<>();
    Map<String, String> paramMap = createParamMap(request);

    ControllerV4 controller = (ControllerV4) handler;

    String viewName = controller.process(paramMap, model);
    ModelView mv = new ModelView(viewName);
    mv.setModel(model);

    return mv;
  }

  private Map<String, String> createParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    req.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
    return paramMap;
  }

}
