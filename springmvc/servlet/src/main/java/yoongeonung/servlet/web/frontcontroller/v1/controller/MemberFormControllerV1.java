package yoongeonung.servlet.web.frontcontroller.v1.controller;

import yoongeonung.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String VIEW_PATH = "/WEB-INF/views/new-form.jsp";
        // 컨트롤러에서 뷰로 넘아갈때 사용, 패스지정 필수, 서버내부에서 호출한다.
        // dispatcher : (열차·버스·비행기 등이 정시 출발하도록 관리하는) 운행 관리원
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request, response);
    }
}
