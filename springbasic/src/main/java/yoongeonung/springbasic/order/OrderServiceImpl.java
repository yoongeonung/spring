package yoongeonung.springbasic.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yoongeonung.springbasic.member.*;

@Component
public class OrderServiceImpl implements OrderService{

    // private final MemberService memberService = new MemberServiceImpl(); // OCP, DIP 위반
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // OCP, DIP 위반

    /**
     * OCP, DIP 만족, 하지만 NullPointerException발생
     * 이 문제를 해결하기 위해서는 제3자가 주입해줄 필요가 있다.
     * 제3자? -> 스프링DI컨테이너
     */
    private final DiscountPolicy discountPolicy; // OCP, DIP 만족
    private final MemberRepository memberRepository; // OCP, DIP 만족

    @Autowired
    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long id, String itemName, int itemPrice) {
        Member member = memberRepository.findById(id);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(id, itemName, itemPrice, discountPrice);
    }

    // only for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
