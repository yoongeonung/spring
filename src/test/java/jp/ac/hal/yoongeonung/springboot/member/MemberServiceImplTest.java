package jp.ac.hal.yoongeonung.springboot.member;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class MemberServiceImplTest {

    private final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    private MemberService memberService;

    @BeforeEach
    void beforeEach() {
//        memberService = new AppConfig().memberService();
        memberService = ac.getBean("memberService", MemberService.class);
    }


    @AfterEach
    void afterEach() {
        memberService.clearAll();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "kakao", Grade.VIP);
        //when
        memberService.join(member);
        Member member1 = memberService.findMember(member.getId());
        //then
        Assertions.assertThat(member).isEqualTo(member1);
        Assertions.assertThat(member.getId()).isEqualTo(member1.getId());

    }

    @Test
    void findMember() {
        //given
        Member member = new Member(1L, "kakao", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());

    }

}