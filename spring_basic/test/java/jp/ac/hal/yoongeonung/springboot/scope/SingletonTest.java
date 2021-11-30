package jp.ac.hal.yoongeonung.springboot.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    @DisplayName("싱글톤 테스트")
    void singletonBeanFind() {
        //given
        /*
        스프링 컨테이너가 올라갈때 싱글톤빈이 생성된다.
         */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        //when
        System.out.println("===================");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("===================");
        //then
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2); // isSameAs -> == 비교

        ac.close();
    }

    @Scope("singleton") // default singleton이라서 지정해줄 필요 없음
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }


        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}
