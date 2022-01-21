package yoongeonung.springbasic.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yoongeonung.springbasic.order.DiscountPolicy;
import yoongeonung.springbasic.order.FixDiscountPolicy;
import yoongeonung.springbasic.order.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    private final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회, 자식 둘 이상 있을시 오류")
    void findBeanByParentTypeDuplicate() {
        assertThatThrownBy(() -> ac.getBean(DiscountPolicy.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("부모타입 조회, 자식 둘 이상, 빈이름 지정하면 됨")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class); // 구체에 의존하는 아주 안좋은 예
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        for (Map.Entry<String, DiscountPolicy> entry : beans.entrySet()) {
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - 오브젝트")
    void findAllBeanByObjectType() {
        Map<String, Object> beans = ac.getBeansOfType(Object.class);
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
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
