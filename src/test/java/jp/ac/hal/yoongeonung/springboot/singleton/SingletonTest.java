package jp.ac.hal.yoongeonung.springboot.singleton;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
