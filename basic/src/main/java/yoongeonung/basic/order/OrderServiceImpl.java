package yoongeonung.basic.order;

import org.springframework.stereotype.Component;
import yoongeonung.basic.discount.DiscountPolicy;
import yoongeonung.basic.member.Member;
import yoongeonung.basic.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // only for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
