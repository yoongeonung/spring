package yoongeonung.basic.order;

import yoongeonung.basic.discount.DiscountPolicy;
import yoongeonung.basic.discount.FixDiscountPolicy;
import yoongeonung.basic.member.Member;
import yoongeonung.basic.member.MemberRepository;
import yoongeonung.basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, "Book", itemPrice, discountPrice);
    }
}
