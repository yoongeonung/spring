package jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v5;

import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.ModelView;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.MyView;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v3.controller.MemberFormControllerV3;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v3.controller.MemberListControllerV3;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v4.controller.MemberFormControllerV4;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v4.controller.MemberListControllerV4;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        // v3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // v4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Object handler = handlerMappingMap.get(uri);
        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(req, resp, handler);

        MyView view = viewResolver(mv);
        view.render(mv.getModel(), req, resp);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("Handler Adapter를 찾을수 없습니다." + handler);
    }

    private MyView viewResolver(ModelView mv) {
        return new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");
    }
}
