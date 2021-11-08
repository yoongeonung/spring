package jp.ac.hal.yoongeonung.springboot.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatelessServiceTest {
    @Test
    @DisplayName("스프링빈을 stateless하게 설계")
    void statelessServiceSingleton() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        //when
        StatelessService statelessService = ac.getBean("statelessService", StatelessService.class);
        Integer kakao = statelessService.order("kakao", 3000);
        Integer line = statelessService.order("line", 1000);
        //then
        Assertions.assertThat(kakao).isNotSameAs(line);
    }

    static class TestConfig {

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}
