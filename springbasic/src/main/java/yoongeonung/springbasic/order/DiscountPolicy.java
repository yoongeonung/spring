package yoongeonung.springbasic.order;

import yoongeonung.springbasic.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인금액 반환
     */
    int discount(Member member, int price);
}
