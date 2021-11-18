package jp.ac.hal.yoongeonung.springboot.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifeCycleTest {

    @Test
    @DisplayName("빈 생명주기 테스트")
    void lifeCycleTest() {
        // ApplicationContext 에는 close() 메서드가 제공되지 않기 때문에 하위 인터페이스를 사용
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
        //when

        //then

    }


    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "destroy")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://hello-spring.dev");
            return networkClient;
        }
    }
}
