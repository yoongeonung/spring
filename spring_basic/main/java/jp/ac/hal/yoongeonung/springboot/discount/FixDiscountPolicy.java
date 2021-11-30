package jp.ac.hal.yoongeonung.springboot.discount;

import jp.ac.hal.yoongeonung.springboot.member.Grade;
import jp.ac.hal.yoongeonung.springboot.member.Member;
import org.springframework.stereotype.Component;

//@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT_FIX_AMOUNT = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT_FIX_AMOUNT;
        } else {
            return 0;
        }
    }
}
