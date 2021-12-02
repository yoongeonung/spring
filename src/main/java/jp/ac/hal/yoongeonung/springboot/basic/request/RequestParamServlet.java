package jp.ac.hal.yoongeonung.springboot.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 정보 조회
 *      localhost:8080/request-param?kakao=good&line=excelent
 * 2. 동일한 파라미터 정보 조회
 *      http://localhost:8080/request-param?company=naver&company=line&company=kakao
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 전체 파리미터 정보 조회
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + req.getParameter(paramName)));
        // 단일 파라미터 정보 조회
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        // 동일 이름의 복수 파라미터의 정보 조회
        String[] companies = req.getParameterValues("username");
        for (String company : companies) {
            System.out.println(company);
        }

        resp.getWriter().write("OK");
    }
}
