package jp.ac.hal.yoongeonung.springboot.order;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import jp.ac.hal.yoongeonung.springboot.member.Grade;
import jp.ac.hal.yoongeonung.springboot.member.Member;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import jp.ac.hal.yoongeonung.springboot.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    // 생성자 주입의 경우 어플리케이션이 뜨면서 생성자를 호출해서 변수들의 초기화가 이뤄지지만
    // 생성자 주입이 아닌 경우 변수의 초기화가 불가능해 final을 붙일 수가 없다.
    private MemberService memberService;
    private OrderService orderService;

    private final ApplicationContext applicationContext= new AnnotationConfigApplicationContext(AppConfig.class);

    @BeforeEach
    void beforeEach() {
//        memberService = new AppConfig().memberService();
//        orderService = new AppConfig().orderService();
        memberService = applicationContext.getBean("memberService", MemberService.class);
        orderService = applicationContext.getBean("orderService", OrderService.class);
    }

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