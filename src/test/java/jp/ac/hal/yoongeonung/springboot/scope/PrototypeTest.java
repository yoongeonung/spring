package jp.ac.hal.yoongeonung.springboot.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    @DisplayName("프로토타입 빈 테스트")
    void prototypeBeanFind() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        //when
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("프로토타입 빈1, 요청시 생성됨을 확인");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("프로토타입 빈2, 요청시 생성됨을 확인");
        //then
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2); // isNotSameAs -> != 비교

        prototypeBean1.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
