package yoongeonung.servlet.web.frontcontroller.v1.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yoongeonung.servlet.domain.member.Member;
import yoongeonung.servlet.domain.member.MemberRepository;
import yoongeonung.servlet.web.frontcontroller.v1.ControllerV1;

public class MemberSaveControllerV1 implements ControllerV1 {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = req.getParameter("username");
    int age = Integer.parseInt(req.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

    req.setAttribute("member", member);
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/save-result.jsp");
    dispatcher.forward(req, resp);

  }
}
