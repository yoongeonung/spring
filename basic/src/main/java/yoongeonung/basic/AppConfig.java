package yoongeonung.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yoongeonung.basic.discount.DiscountPolicy;
import yoongeonung.basic.discount.RateDiscountPolicy;
import yoongeonung.basic.member.MemberRepository;
import yoongeonung.basic.member.MemberService;
import yoongeonung.basic.member.MemberServiceImpl;
import yoongeonung.basic.member.MemoryMemberRepository;
import yoongeonung.basic.order.OrderService;
import yoongeonung.basic.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
     public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
