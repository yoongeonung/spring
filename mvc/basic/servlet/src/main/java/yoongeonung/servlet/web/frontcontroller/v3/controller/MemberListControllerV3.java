package yoongeonung.servlet.web.frontcontroller.v3.controller;

import java.util.List;
import java.util.Map;
import yoongeonung.servlet.domain.member.Member;
import yoongeonung.servlet.domain.member.MemberRepository;
import yoongeonung.servlet.web.frontcontroller.ModelView;
import yoongeonung.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3 {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {

    List<Member> members = memberRepository.findAll();

    ModelView modelView = new ModelView("members");
    modelView.getModel().put("members", members);
    return modelView;
  }
}
