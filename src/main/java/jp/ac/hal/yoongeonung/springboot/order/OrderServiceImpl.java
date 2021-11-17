package jp.ac.hal.yoongeonung.springboot.order;

import jp.ac.hal.yoongeonung.springboot.annotation.MainDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.member.Member;
import jp.ac.hal.yoongeonung.springboot.member.MemberRepository;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    DIP 위반 OCP 위반
//    private final MemberService memberService = new MemberServiceImpl();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberService memberService;
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

//    롬복으로 대체
    @Autowired
    public OrderServiceImpl(MemberService memberService, @MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberService.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
