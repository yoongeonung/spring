package yoongeonung.basic.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.basic.AutoAppConfig;
import yoongeonung.basic.order.OrderService;

public class AutoAppConfigTest {
    @Test
    void basicScan() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
    }
}
