package yoongeonung.basic.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import yoongeonung.basic.member.Grade;
import yoongeonung.basic.member.Member;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private static final int DISCOUNT_RATE = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * DISCOUNT_RATE / 100;
        }
        return 0;
    }
}
