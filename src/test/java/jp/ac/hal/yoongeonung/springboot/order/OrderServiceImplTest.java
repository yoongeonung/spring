package jp.ac.hal.yoongeonung.springboot.order;

import jp.ac.hal.yoongeonung.springboot.member.Grade;
import jp.ac.hal.yoongeonung.springboot.member.Member;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import jp.ac.hal.yoongeonung.springboot.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "kakao", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}