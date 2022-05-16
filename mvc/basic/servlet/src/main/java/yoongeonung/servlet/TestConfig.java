package yoongeonung.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class TestConfig {

  @Bean
  ViewResolver internalResourceViewResolver() {
    return new InternalResourceViewResolver(
        "/WEB-INF/views/", ".jsp"
    );
  }

}
