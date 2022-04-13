package yoongeonung.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void statefulServiceSingleton() {
        //given
        //when
        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        int price = statefulService1.getPrice();
        System.out.println("userA.price = " + price);
        //then
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    @Test
    void statelessServiceSingleton() {
        //given
        //when
        StatelessService statelessService1 = applicationContext.getBean(StatelessService.class);
        StatelessService statelessService2 = applicationContext.getBean(StatelessService.class);

        int orderPrice1 = statelessService1.order("userA", 10000);
        int orderPrice2 = statelessService2.order("userB", 20000);

        System.out.println("orderPrice1 = " + orderPrice1);
        System.out.println("orderPrice2 = " + orderPrice2);
        //then
        Assertions.assertThat(orderPrice1).isNotSameAs(orderPrice2);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }


}