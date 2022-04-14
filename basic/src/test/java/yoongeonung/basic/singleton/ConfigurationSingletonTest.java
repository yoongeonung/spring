package yoongeonung.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.basic.AppConfig;
import yoongeonung.basic.member.MemberRepository;
import yoongeonung.basic.member.MemberServiceImpl;
import yoongeonung.basic.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    void configurationTest() {
        /**
         * testの為に具体タイプで照会
         */
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);
        //when
        System.out.println("memberService = " + memberService.getMemberRepository());
        System.out.println("orderService = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);
        /*
            memberService = @6d026701
            orderService = @6d026701
            memberRepository = @6d026701
            みんな同じmemberRepositoryのインスタンスを参照している。
         */
        //then
        Assertions.assertThat(memberRepository).isSameAs(memberService.getMemberRepository());
        Assertions.assertThat(memberRepository).isSameAs(orderService.getMemberRepository());
    }
}
