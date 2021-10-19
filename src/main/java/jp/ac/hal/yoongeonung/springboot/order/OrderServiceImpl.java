package jp.ac.hal.yoongeonung.springboot.order;

import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.FixDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.member.Member;
import jp.ac.hal.yoongeonung.springboot.member.MemberRepository;
import jp.ac.hal.yoongeonung.springboot.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
