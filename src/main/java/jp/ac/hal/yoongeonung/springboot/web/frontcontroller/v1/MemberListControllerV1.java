package jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v1;

import jp.ac.hal.yoongeonung.springboot.domain.member.Member;
import jp.ac.hal.yoongeonung.springboot.domain.member.MemberRepository;
import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        // View로 forward
        String VIEW_PATH = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request, response);
    }
}
