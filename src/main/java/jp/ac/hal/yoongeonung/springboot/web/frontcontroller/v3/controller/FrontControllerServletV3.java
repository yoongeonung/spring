package jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v3.controller;

import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.ModelView;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.MyView;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {


    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        ControllerV3 controller = controllerMap.get(uri);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);

        ModelView mv = controller.process(paramMap);

        MyView view = viewResolver(mv);
        view.render(mv.getModel(), req, resp);
    }

    private MyView viewResolver(ModelView mv) {
        MyView view = new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");
        return view;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
