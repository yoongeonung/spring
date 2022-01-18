package yoongeonung.springbasic;

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
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }
    // 역할의 명확화. 메서드로 독립시킴으로서 어떤 역할을 하는지 확실히 할 수 있다.
    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }

    // 역할의 명확화. 메서드로 독립시킴으로서 어떤 역할을 하는지 확실히 할 수 있다.
    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
