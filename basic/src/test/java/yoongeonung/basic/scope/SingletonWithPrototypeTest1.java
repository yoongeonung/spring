package yoongeonung.basic.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
    
    @Test
    void prototypeFind() {
        //given
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        //when
        prototypeBean1.addCount();
        prototypeBean2.addCount();
        //then
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        //given
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = applicationContext.getBean(ClientBean.class);
        ClientBean clientBean2 = applicationContext.getBean(ClientBean.class);
        //when
        int count1 = clientBean1.logic();
        System.out.println("count = " + count1);
        int count2 = clientBean2.logic();
        System.out.println("count2 = " + count2);
        //then
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
    }
    
    @Scope("prototype")
    static class PrototypeBean {

        private int count;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    @Scope
    static class ClientBean {
        private final ObjectProvider<PrototypeBean> objectProvider;

        public ClientBean(ObjectProvider<PrototypeBean> objectProvider) {
            this.objectProvider = objectProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = objectProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }
}
