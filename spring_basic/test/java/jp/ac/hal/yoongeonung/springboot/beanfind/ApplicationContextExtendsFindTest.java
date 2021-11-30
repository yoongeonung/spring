package jp.ac.hal.yoongeonung.springboot.beanfind;

import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.FixDiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    private final ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상일 경우 오류가 발생")
    void findBeanByParentType() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상일 경우 빈 이름을 지정")
    void findBeanByParentTypeAndName() {
        //given
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        //when
        //then
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
        // 추천하지 않는 방법, 구현에 의존해서는 안좋다.
    void findBeanBySubType() {
        //given
        RateDiscountPolicy discountPolicy = ac.getBean(RateDiscountPolicy.class);
        //when
        //then
        assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회")
    void findAllBeanByParentType() {
        //given
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        //when
        //then
        beans.forEach((key, value) -> {
            assertThat(beans).containsKeys(key);
            assertThat(beans).containsValue(value);
        });
        assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }
}
