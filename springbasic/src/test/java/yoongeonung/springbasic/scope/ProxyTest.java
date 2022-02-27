package yoongeonung.springbasic.scope;

import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.annotation.PostConstruct;

public class ProxyTest {
    @Test
    @DisplayName("프록시 객체는 애플리케이션 실행시 컨테이너에 등록되는가?")
    void proxyFindTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProxyWeb.class,ProxyProto.class);
        ProxyWeb bean = ac.getBean(ProxyWeb.class);
        System.out.println("bean.getClass() = " + bean.getClass());
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            Object object = ac.getBean(name);
            System.out.println("object = " + object.getClass());
        }
    }

    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    static class ProxyWeb {
        @PostConstruct
        public void init() {
            System.out.println("ProxyWeb.init : " + this.getClass());
        }
    }

    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    static class ProxyProto {
        @PostConstruct
        public void init() {
            System.out.println("ProxyProto.init : " + this.getClass());
        }
    }
}
