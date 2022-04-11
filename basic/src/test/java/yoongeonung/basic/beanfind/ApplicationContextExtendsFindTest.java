package yoongeonung.basic.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yoongeonung.basic.discount.DiscountPolicy;
import yoongeonung.basic.discount.FixDiscountPolicy;
import yoongeonung.basic.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> applicationContext.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        //given
        DiscountPolicy rateDiscountPolicy = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);
        //when
        //then
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        //given
        RateDiscountPolicy rateDiscountPolicy = applicationContext.getBean(RateDiscountPolicy.class);
        //when
        //then
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        //given
        Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);
        //when
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", bean = " + applicationContext.getBean(key));
        }
        //then
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("오브젝트 타입으로 모두 조회하기")
    void findAllBeanByObjectType() {
        //given
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        //when
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", bean = " + applicationContext.getBean(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

}
