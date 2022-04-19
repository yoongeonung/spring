package yoongeonung.basic.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import yoongeonung.basic.member.Member;

import java.util.Optional;

public class AutowiredTest {
    
    @Test
    void autowiredTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    static class TestConfig {

        @Autowired(required = false)
        public void autowired1(Member nobean1) {
            System.out.println("nobean1 = " + nobean1);
        }

        @Autowired
        public void autowired2(@Nullable Member nobean2) {
            System.out.println("nobean2 = " + nobean2);
        }

        @Autowired
        public void autowired3(Optional<Member> nobean3) {
            System.out.println("nobean3 = " + nobean3);
        }
    }
}
