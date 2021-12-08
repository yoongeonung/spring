package jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v2;

import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.MyView;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v2.controller.MemberFormControllerV2;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v2.controller.MemberListControllerV2;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private final Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        ControllerV2 controller = controllerMap.get(uri);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView myView = controller.process(req, resp);
        myView.render(req, resp);
    }
}
