package yoongeonung.basic.discount;

import yoongeonung.basic.member.Grade;
import yoongeonung.basic.member.Member;

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
