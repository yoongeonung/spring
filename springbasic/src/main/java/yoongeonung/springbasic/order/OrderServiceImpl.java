package yoongeonung.springbasic.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import yoongeonung.springbasic.annotation.MainDiscountPolicy;
import yoongeonung.springbasic.member.*;

@Component
//@RequiredArgsConstructor
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

//    Lombok의 @RequiredArgsConstructor를 사용해서 생성자 생략가능
    @Autowired // 생성자가 클래스내에 1개이고 스프링빈일시 @Autowired 생략가능
    public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        System.out.println("constructor discountPolicy = " + discountPolicy);
        System.out.println("constructor memberRepository = " + memberRepository);
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

//    @Autowired // setter注入
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("setter discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired // setter注入
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("setter memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

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
