package yoongeonung.basic.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import yoongeonung.basic.member.Grade;
import yoongeonung.basic.member.Member;

@Component
@Qualifier("subDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    private static final int FIX_DISCOUNT_AMOUNT = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return FIX_DISCOUNT_AMOUNT;
        }
        return 0;
    }
}
