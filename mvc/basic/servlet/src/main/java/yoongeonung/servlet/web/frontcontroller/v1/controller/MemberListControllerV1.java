package yoongeonung.servlet.web.frontcontroller.v1.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.domain.member.Member;
import yoongeonung.servlet.domain.member.MemberRepository;
import yoongeonung.servlet.web.frontcontroller.v1.ControllerV1;

public class MemberListControllerV1 implements ControllerV1 {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Member> members = memberRepository.findAll();

    req.setAttribute("members", members);
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/members.jsp");
    dispatcher.forward(req, resp);
  }
}
