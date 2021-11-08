package jp.ac.hal.yoongeonung.springboot.singleton;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링없는 순수 DI컨테이너")
    void nonSpringContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회
        MemberService memberService2 = appConfig.memberService();
        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴")
    void singletonServiceTest() {
        // given
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        // when
        // then
        Assertions.assertThat(singletonService1).isInstanceOf(SingletonService.class);
        // isSameAs : == 비교
        // isEqualTo : equals를 이용한 비교
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너")
    void springContainer() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //when
        MemberService memberSerivce1 = ac.getBean("memberService", MemberService.class);
        MemberService memberSerivce2 = ac.getBean("memberService", MemberService.class);
        //then
        Assertions.assertThat(memberSerivce1).isSameAs(memberSerivce2);
    }
}
