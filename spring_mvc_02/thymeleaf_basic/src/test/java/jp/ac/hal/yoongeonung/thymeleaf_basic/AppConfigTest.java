package jp.ac.hal.yoongeonung.thymeleaf_basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    private final AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(AppConfig.class);

    // spring container에 등록되어있는 모든 빈을 조회
    @Test
    void findAllBean() {
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
//            System.out.println("beanDefinitionName = " + beanDefinitionName + ",  object = " + ac.getBean(beanDefinitionName));
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object=" + bean);

                //name = basicController.HelloBean object=jp.ac.hal.yoongeonung.thymeleaf_basic.basic.BasicController$HelloBean@5cf8edcf
                //name = helloBean object=jp.ac.hal.yoongeonung.thymeleaf_basic.AppConfig$HelloBean@1ae8bcbc
            }
        }
    }
}