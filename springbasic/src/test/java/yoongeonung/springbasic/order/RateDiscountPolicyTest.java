package yoongeonung.springbasic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yoongeonung.springbasic.member.Grade;
import yoongeonung.springbasic.member.Member;
import yoongeonung.springbasic.member.MemberService;
import yoongeonung.springbasic.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    void vip() {
        //given
        Member member = new Member(1L, "Yoon", Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(1L, "Spring", 3000);
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(300);
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