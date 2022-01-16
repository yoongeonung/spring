package yoongeonung.springbasic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import yoongeonung.springbasic.member.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        //given
        Member member = new Member(1L, "Spring", Grade.VIP);
        //when
        memberService.join(member);
        Order order = orderService.createOrder(member.getId(), "SpringBook", 3000);
        //then
        Assertions.assertThat(order.calculatePrice()).isEqualTo(2000);  // 할인적용된 상품금액
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // 정액 할인 금액

    }

}