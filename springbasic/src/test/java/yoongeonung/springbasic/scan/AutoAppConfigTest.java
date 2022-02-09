package yoongeonung.springbasic.scan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.springbasic.AutoAppConfig;
import yoongeonung.springbasic.common.MyLogger;

public class AutoAppConfigTest {
    @Test
    @DisplayName("Component scan test")
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MyLogger bean = ac.getBean(MyLogger.class);
        System.out.println("bean = " + bean);
    }
}
