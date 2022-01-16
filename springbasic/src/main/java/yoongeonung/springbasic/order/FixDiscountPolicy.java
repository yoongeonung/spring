package yoongeonung.springbasic.order;

import yoongeonung.springbasic.member.Grade;
import yoongeonung.springbasic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private final int DISCOUNT_FIX_AMOUNT = 1000;

    @Override
    public int discount(Member member, int price) {
        // enum 타입은 ==으로 비교하는게 맞다.
        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT_FIX_AMOUNT;
        }
        return 0;
    }
}
