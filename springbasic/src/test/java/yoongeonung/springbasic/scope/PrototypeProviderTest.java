package yoongeonung.springbasic.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeProviderTest {

    @Test
    @DisplayName("필요시마다 컨테이너에 요청")
    void providerTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count = bean1.logic();
        assertThat(count).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    @RequiredArgsConstructor
    static class ClientBean {

//        private final ApplicationContext ac;
        private final ObjectProvider<PrototypeBean> provider;

        public int logic() {
            // 필요시마다 컨테이너에 직접 요청 -> 이런 방법을 Dependency Lookup (DL) 이라고 한다.
            PrototypeBean prototypeBean = provider.getObject();
            System.out.println("prototypeBean = " + prototypeBean);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope(value = "prototype")
    static class PrototypeBean {
        @Getter
        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}
