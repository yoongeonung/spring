package yoongeonung.springbasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yoongeonung.springbasic.member.MemberService;
import yoongeonung.springbasic.member.MemberServiceImpl;
import yoongeonung.springbasic.member.MemoryMemberRepository;
import yoongeonung.springbasic.order.*;

/**
 * 제3자
 * 관심사의 분리!
 * 역할은 역할에 충실
 * AppConfig가 구현체를 주입
 */
@Configuration
public class AppConfig {

    /**
     * Spring Container에
     * key - method이름
     * value - new MemberServiceImpl(memberRepository())
     * 로서 등록된다.
     */
    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
//        return new OrderServiceImpl(discountPolicy(), memberRepository());
        return null;
    }

    // 역할의 명확화. 메서드로 독립시킴으로서 어떤 역할을 하는지 확실히 할 수 있다.
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    // 역할의 명확화. 메서드로 독립시킴으로서 어떤 역할을 하는지 확실히 할 수 있다.
    // 중복을 제거하고, 역할에 따른 구현이 보이도록 리팩터링
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
}
