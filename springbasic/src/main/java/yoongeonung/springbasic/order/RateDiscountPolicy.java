package yoongeonung.springbasic.order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import yoongeonung.springbasic.annotation.MainDiscountPolicy;
import yoongeonung.springbasic.member.Grade;
import yoongeonung.springbasic.member.Member;

//@Primary
@MainDiscountPolicy
@Component
public class RateDiscountPolicy implements DiscountPolicy{

    /**
     * 10% Discount
     */
    private final int DISCOUNT_PERCENT = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return (price * DISCOUNT_PERCENT) / 100;
        }
        return 0;
    }
}
