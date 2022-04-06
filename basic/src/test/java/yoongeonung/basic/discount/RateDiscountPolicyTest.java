package yoongeonung.basic.discount;

import org.junit.jupiter.api.Test;
import yoongeonung.basic.member.Grade;
import yoongeonung.basic.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    void discountVIP() {
        //given
        Member member = new Member();
        member.setGrade(Grade.VIP);
        member.setName("memberVIP");
        member.setId(1L);
        //when
        int discountAmount = discountPolicy.discount(member, 36_580);
        //then
        assertThat(discountAmount).isEqualTo(3_658);
    }

    @Test
    void doNotDiscout() {
        //given
        Member member = new Member();
        member.setGrade(Grade.BASIC);
        member.setName("memberBASIC");
        member.setId(1L);
        //when
        int discountAmout = discountPolicy.discount(member, 10000);
        //then
        assertThat(discountAmout).isEqualTo(0);
    }

}