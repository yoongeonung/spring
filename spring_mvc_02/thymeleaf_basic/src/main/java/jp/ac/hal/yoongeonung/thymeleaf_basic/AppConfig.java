package jp.ac.hal.yoongeonung.thymeleaf_basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public HelloBean helloBean()  {
        return new HelloBean();
    }

    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }
}
