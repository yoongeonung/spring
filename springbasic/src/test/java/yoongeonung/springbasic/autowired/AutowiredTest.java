package yoongeonung.springbasic.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import yoongeonung.springbasic.member.Member;

import java.util.Optional;

public class AutowiredTest {


    @Test
    void autowiredTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }

    static class TestConfig {
        //호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = " + member);
        }
        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }
        //Optional.empty 호출
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }
    }
}
