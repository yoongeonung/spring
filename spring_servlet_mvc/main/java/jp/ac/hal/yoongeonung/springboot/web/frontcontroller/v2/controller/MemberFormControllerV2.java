package jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v2.controller;

import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.MyView;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
