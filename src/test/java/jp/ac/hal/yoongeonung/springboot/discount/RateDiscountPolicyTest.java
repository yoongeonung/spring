package jp.ac.hal.yoongeonung.springboot.discount;

import jp.ac.hal.yoongeonung.springboot.member.Grade;
import jp.ac.hal.yoongeonung.springboot.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인")
    void discount() {
        //given
        Member member = new Member(1L, "test", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP아닐시 할인 안됨")
    void failedDiscount() {
        //given
        Member member = new Member(1L, "basic", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}