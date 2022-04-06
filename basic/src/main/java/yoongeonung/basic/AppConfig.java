package yoongeonung.basic;

import yoongeonung.basic.discount.FixDiscountPolicy;
import yoongeonung.basic.member.MemberService;
import yoongeonung.basic.member.MemberServiceImpl;
import yoongeonung.basic.member.MemoryMemberRepository;
import yoongeonung.basic.order.OrderService;
import yoongeonung.basic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
