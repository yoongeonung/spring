package jp.ac.hal.yoongeonung.springboot.discount;

import jp.ac.hal.yoongeonung.springboot.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
