package yoongeonung.springbasic.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {
    @Test
    @DisplayName("프로토타입 빈 직접 조회")
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("싱글톤 빈에서 프로토타입빈을 함께 사용시")
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        assertThat(count1).isEqualTo(1);
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isEqualTo(2);
    }

    @RequiredArgsConstructor
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        @Getter
        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}