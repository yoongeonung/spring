package jp.ac.hal.yoongeonung.springboot.singleton;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import jp.ac.hal.yoongeonung.springboot.member.MemberServiceImpl;
import jp.ac.hal.yoongeonung.springboot.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    @DisplayName("스프링 컨테이너 싱글톤 확인")
    void configurationTest() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //when
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        //then
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());

    }
}
