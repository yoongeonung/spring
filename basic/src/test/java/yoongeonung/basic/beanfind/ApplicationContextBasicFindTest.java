package yoongeonung.basic.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yoongeonung.basic.AppConfig;
import yoongeonung.basic.discount.RateDiscountPolicy;
import yoongeonung.basic.member.MemberService;
import yoongeonung.basic.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanBySpecificType() {
        RateDiscountPolicy rateDiscountPolicy = applicationContext.getBean("discountPolicy",RateDiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("없는 빈 이름으로 조회: 실패테스트")
    void findBeanByNotExistName() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean("notExistBean", MemberService.class));
    }
}
