package yoongeonung.basic.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    
    @Test
    void lifeCycleTest() {
        //given
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = applicationContext.getBean("networkClient", NetworkClient.class);
        // close()는 ConfigurableApplicationContext, 혹은 ApplicationContext를 상속한 클래스 필요
        applicationContext.close();
        //when
        
        //then
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://spring.dev");
            return networkClient;
        }
    }
}
