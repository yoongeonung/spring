package jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v1;

import jp.ac.hal.yoongeonung.springboot.domain.member.Member;
import jp.ac.hal.yoongeonung.springboot.domain.member.MemberRepository;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 member를 보관
        request.setAttribute("member", member);

        final String VIEW_PATH = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request, response);
    }
}
