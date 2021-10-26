package jp.ac.hal.yoongeonung.springboot;

import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.FixDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.member.MemberRepository;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import jp.ac.hal.yoongeonung.springboot.member.MemberServiceImpl;
import jp.ac.hal.yoongeonung.springboot.member.MemoryMemberRepository;
import jp.ac.hal.yoongeonung.springboot.order.OrderService;
import jp.ac.hal.yoongeonung.springboot.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberService(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
