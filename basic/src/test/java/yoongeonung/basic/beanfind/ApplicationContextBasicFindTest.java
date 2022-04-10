package yoongeonung.basic.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.basic.AppConfig;
import yoongeonung.basic.member.MemberService;
import yoongeonung.basic.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void findBeanByName() {
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    void findBeanByType() {
        //given
        MemberService memberService = applicationContext.getBean(MemberService.class);
        //when
        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
