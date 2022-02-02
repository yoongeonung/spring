package yoongeonung.springbasic.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.springbasic.AutoAppConfig;
import yoongeonung.springbasic.member.Grade;
import yoongeonung.springbasic.member.Member;
import yoongeonung.springbasic.order.DiscountPolicy;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    @DisplayName("모든 빈 객체가 필요할때")
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "Kakao", Grade.VIP);
        int discountPrice = discountService.discount(member, 20000, DiscountType.rateDiscountPolicy);
        Assertions.assertThat(discountPrice).isEqualTo(2000);
    }

    private static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired // 생략가능
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        private int discount(Member member, int price, DiscountType discountType) {
            System.out.println("discountType = " + discountType.toString());
            System.out.println("discountType = " + discountType.name());
            DiscountPolicy discountPolicy = policyMap.get(discountType.toString());
            return discountPolicy.discount(member, price);
        }
    }
}
