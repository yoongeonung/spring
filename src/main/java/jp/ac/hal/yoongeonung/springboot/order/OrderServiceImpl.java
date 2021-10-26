package jp.ac.hal.yoongeonung.springboot.order;

import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.FixDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.RateDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.member.*;

public class OrderServiceImpl implements OrderService{

    private final MemberService memberService = new MemberServiceImpl();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberService.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
