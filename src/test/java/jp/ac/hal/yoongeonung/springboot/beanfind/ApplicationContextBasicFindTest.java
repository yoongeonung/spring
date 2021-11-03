package jp.ac.hal.yoongeonung.springboot.beanfind;

import jp.ac.hal.yoongeonung.springboot.AppConfig;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import jp.ac.hal.yoongeonung.springboot.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    private final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("bean 이름으로 조회")
    void findBeanByName() {
        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //when
        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("bean type으로 조회")
    void findBeanByType() {
        //given
        MemberService bean = ac.getBean(MemberService.class);
        //when
        //then
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("bean의 구체타입으로 조회")
    void findBeanBySpecificType() {
        //given
        MemberServiceImpl bean = ac.getBean(MemberServiceImpl.class);
        //when
        //then
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("bean조회 실패")
    void noFindBean() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("nobean", MemberService.class));
    }
}
