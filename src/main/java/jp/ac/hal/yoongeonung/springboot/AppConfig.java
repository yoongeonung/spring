package jp.ac.hal.yoongeonung.springboot;

import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.RateDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.member.MemberRepository;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import jp.ac.hal.yoongeonung.springboot.member.MemberServiceImpl;
import jp.ac.hal.yoongeonung.springboot.member.MemoryMemberRepository;
import jp.ac.hal.yoongeonung.springboot.order.OrderService;
import jp.ac.hal.yoongeonung.springboot.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {// 메서드 이름만 봐도 어떤 역할인지 한눈에 파악할수 있게 변경
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberService(), discountPolicy(), memberRepository());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
