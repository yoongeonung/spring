package jp.ac.hal.yoongeonung.springboot.web.springmvc.v3;

import jp.ac.hal.yoongeonung.springboot.domain.member.Member;
import jp.ac.hal.yoongeonung.springboot.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @RequestMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
//        ModelAndView mv = new ModelAndView("members");
//        mv.addObject("members", members);

        model.addAttribute("members", members);
        return "members";
    }

    @RequestMapping("/save")
    // public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
//        String username = request.getParameter("username");
//        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

//        ModelAndView mv = new ModelAndView("save-result");
//        mv.addObject("member", member);
        model.addAttribute("member", member);
        return "save-result";
    }

}
