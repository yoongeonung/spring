package yoongeonung.springbasic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {

    @Test
    @DisplayName("상태를 유지하는 필드")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : 유저A 사용자가 10000원 주문
        statefulService1.order("UserA", 10000);

        // ThreadB : 유저B 사용자가 20000원 주문
        statefulService2.order("UserB", 20000);

        // 의도와는 다르게 2000원이 출력
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        Assertions.assertThat(price).isEqualTo(20000); // 개망
    }

    @Test
    @DisplayName("상태를 유지하지 않는 필드")
    void statelessServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

        int userAPrice = statelessService1.order("UserA", 10000);
        int userBPrice = statelessService2.order("UserB", 20000);

        Assertions.assertThat(userAPrice).isEqualTo(10000); // 의도대로 10000
        Assertions.assertThat(userBPrice).isEqualTo(20000); // 의도대로 20000

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