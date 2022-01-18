package yoongeonung.springbasic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yoongeonung.springbasic.member.Grade;
import yoongeonung.springbasic.member.Member;

class RateDiscountPolicyTest {

    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    void vip() {
        //given
        Member member = new Member(1L, "Yoon", Grade.VIP);
        //when
        int discountPrice = discountPolicy.discount(member, 3000);
        //then
        Assertions.assertThat(discountPrice).isEqualTo(300);
    }

    @Test
    @DisplayName("VIP가 아닐경우")
    void noVip() {
        //given
        Member member = new Member(1L, "Yoon", Grade.BASIC);
        //when
        int discountPrice = discountPolicy.discount(member, 3000);
        //then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

}