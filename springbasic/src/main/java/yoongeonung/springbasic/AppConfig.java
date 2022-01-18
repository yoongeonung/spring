package yoongeonung.springbasic;

import yoongeonung.springbasic.member.MemberService;
import yoongeonung.springbasic.member.MemberServiceImpl;
import yoongeonung.springbasic.member.MemoryMemberRepository;
import yoongeonung.springbasic.order.OrderService;
import yoongeonung.springbasic.order.OrderServiceImpl;
import yoongeonung.springbasic.order.RateDiscountPolicy;

/**
 * 제3자
 * 관심사의 분리!
 * 역할은 역할에 충실
 * AppConfig가 구현체를 주입
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new RateDiscountPolicy(), memberService());
    }
}
