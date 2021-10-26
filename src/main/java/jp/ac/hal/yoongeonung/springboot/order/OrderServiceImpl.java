package jp.ac.hal.yoongeonung.springboot.order;

import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.member.Member;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;

public class OrderServiceImpl implements OrderService{

//    private final MemberService memberService = new MemberServiceImpl();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberService memberService;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberService memberService, DiscountPolicy discountPolicy) {
        this.memberService = memberService;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberService.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
