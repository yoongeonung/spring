package jp.ac.hal.yoongeonung.springboot.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {
    @Test
    @DisplayName("프로토타입 로직 실행 테스트")
    void prototypeFind() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        //when
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int bean1Count = clientBean1.logic();
        int bean2Count = clientBean2.logic();
        //then
        Assertions.assertThat(bean1Count).isEqualTo(1);
        Assertions.assertThat(bean2Count).isEqualTo(1);
//        Assertions.assertThat(bean1Count).isNotSameAs(bean2Count);


        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @Getter
        private int count = 0;

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        /**
         * prototype 빈이기때문에 생성후 클라이언트에게 던진후 컨테이너는 신경안쓴다 그렇기 떄문에 PreDestroy는 작동안함.
         * 수동으로 직접 호출해줘야 한다.
         */
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

        public void addCount() {
            count++;
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {

        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        private PrototypeBean getBean() {
            return prototypeBeanProvider.getObject();
        }

        public int logic() {
            PrototypeBean prototypeBean = getBean();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        @PreDestroy
        public void destroy() {
            PrototypeBean prototypeBean = getBean();
            prototypeBean.destroy();
        }
    }

}
