package jp.ac.hal.yoongeonung.springboot.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    @DisplayName("상태를 유지하게 설계했을 경우")
    void statefulServiceSingleton() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        //when
        StatefulService statefulService = ac.getBean("statefulService", StatefulService.class);
        statefulService.order("kakao", 1000);
        statefulService.order("line", 2000);
        //then
        /**
         * 싱글톤 방식에 상태률 유지하게 설계하여 첫번째 주문 내역을 두번째 주문내역이 덮어 씌우게 된다.
         * 스프링빈은 항상 무상태로 설계 할것!!
         */
        org.assertj.core.api.Assertions.assertThat(statefulService.getPrice()).isEqualTo(2000);
    }


    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}

