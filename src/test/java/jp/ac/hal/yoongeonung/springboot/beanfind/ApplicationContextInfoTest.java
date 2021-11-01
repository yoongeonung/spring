package jp.ac.hal.yoongeonung.springboot.beanfind;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    private final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // spring container에 등록되어있는 모든 빈을 조회
    @Test
    void findAllBean() {
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName + ",  object = " + ac.getBean(beanDefinitionName));
        }
    }

    /**
     * Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
     * Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
     */
    @Test
    void findApplicationBeans() {
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName);
            }
        }
    }
}
