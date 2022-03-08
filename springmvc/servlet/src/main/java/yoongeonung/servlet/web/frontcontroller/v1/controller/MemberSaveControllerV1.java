package yoongeonung.servlet.web.frontcontroller.v1.controller;

import yoongeonung.servlet.domain.member.Member;
import yoongeonung.servlet.domain.member.MemberRepository;
import yoongeonung.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String VIEW_PATH = "/WEB-INF/views/save-result.jsp";

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member();
        member.setUsername(username);
        member.setAge(age);
        memberRepository.save(member);

        request.setAttribute("member", member);

        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request, response);
    }
}
