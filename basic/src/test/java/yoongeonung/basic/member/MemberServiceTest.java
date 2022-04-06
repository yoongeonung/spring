package yoongeonung.basic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private final MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setId(1L);
        member.setName("basic");
        member.setGrade(Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        //then
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember.getGrade()).isEqualTo(member.getGrade());
    }


}