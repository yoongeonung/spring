package jp.ac.hal.yoongeonung.springboot.member;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    private MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memberService = new AppConfig().memberService();
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