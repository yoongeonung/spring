package yoongeonung.springbasic.order;

import yoongeonung.springbasic.member.*;

public class OrderServiceImpl implements OrderService{

    private final MemberService memberService = new MemberServiceImpl();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long id, String itemName, int itemPrice) {
        Member member = memberService.findMember(id);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(id, itemName, itemPrice, discountPrice);
    }
}
