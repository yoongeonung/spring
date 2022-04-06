package yoongeonung.basic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import yoongeonung.basic.member.Grade;
import yoongeonung.basic.member.Member;
import yoongeonung.basic.member.MemberService;
import yoongeonung.basic.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        //given
        Member member = new Member();
        member.setId(1L);
        member.setName("kakao");
        member.setGrade(Grade.VIP);

        memberService.join(member);
        //when
        Order order = orderService.createOrder(member.getId(), "Book", 10000);
        System.out.println("order = " + order);
        //then
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}