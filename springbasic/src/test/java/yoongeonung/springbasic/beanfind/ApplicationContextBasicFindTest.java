package yoongeonung.springbasic.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.springbasic.AppConfig;
import yoongeonung.springbasic.member.MemberService;
import yoongeonung.springbasic.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        // given
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // when
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로만 조회")
    void findBeanByType() {
        // given
        MemberService memberService = ac.getBean(MemberService.class);
        // when
        // thne
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByImpl() {
        // given
        /**
         * 역할과 구현의 분리를 기억하자!
         * 역할에 의존해야지 구현(구체)에 의존해서는 안된다.
         * 코드의 유연성이 떨어진다.
         */
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // when
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("조회 실패")
    void findBeanFailed() {
        // given
        // then
        assertThatThrownBy(() -> ac.getBean("noBeanService", MemberService.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
