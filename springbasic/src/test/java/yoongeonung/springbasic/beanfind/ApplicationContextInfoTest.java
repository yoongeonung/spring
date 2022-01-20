package yoongeonung.springbasic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.springbasic.AppConfig;

public class ApplicationContextInfoTest {
    // 설정 클래스 또한 빈으로 등록된다.
    // 설정 클래스를 사용한 스피링 컨테이너 생성시 부가 기능이 더 많은 클래스 => AnnotationConfigApplicationContext
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = ac.getBean(name);
            System.out.println("name = " + name + "object = " +bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBean() {
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition bd = ac.getBeanDefinition(name);
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(name);
                System.out.println("name = " + name + "object = " + bean);
            }
        }
    }
}
