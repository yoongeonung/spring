package yoongeonung.springbasic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yoongeonung.springbasic.AppConfig;
import yoongeonung.springbasic.member.Grade;
import yoongeonung.springbasic.member.Member;
import yoongeonung.springbasic.member.MemberService;

import static org.junit.jupiter.api.Assertions.*;

class FixDiscountPolicyTest {

    private OrderService orderService;
    private MemberService memberService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @Test
    void vip() {
        //given
        Member member = new Member(1L, "Yoon", Grade.VIP);
        //when
        memberService.join(member);
        Order order = orderService.createOrder(1L, "Spring", 3000);
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}