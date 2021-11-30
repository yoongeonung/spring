package jp.ac.hal.yoongeonung.springboot.nobean;

import jp.ac.hal.yoongeonung.springboot.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class NoBeanTest {
    @Test
    @DisplayName("No Bean Test")
    void noBeanTest() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(NoBeanConfig.class);
        //when
        //then
    }

    static class NoBeanConfig {

        @Autowired(required = false)
        public void setNoBean(Member nobean1) {
            System.out.println("nobean1 = " + nobean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
