package yoongeonung.basic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yoongeonung.basic.AppConfig;
import yoongeonung.basic.member.Grade;
import yoongeonung.basic.member.Member;
import yoongeonung.basic.member.MemberService;

class OrderServiceTest {

    private MemberService memberService;
    private OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        this.memberService = appConfig.memberService();
        this.orderService = appConfig.orderService();
    }

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