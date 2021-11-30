package jp.ac.hal.yoongeonung.springboot;

import jp.ac.hal.yoongeonung.springboot.discount.FixDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.RateDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountConfig {

    @Bean
    public FixDiscountPolicy fixDiscountPolicy() {
        return new FixDiscountPolicy();
    }

    @Bean
    public RateDiscountPolicy rateDiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
