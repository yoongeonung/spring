package yoongeonung.springbasic.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yoongeonung.springbasic.member.MemberRepository;
import yoongeonung.springbasic.member.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로만 조회, 동일 타입의 빈 두개 이상시 예외발생")
    void findBeanByTypeDuplicate() {
        assertThatThrownBy(() -> ac.getBean(MemberRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입의 빈이 둘이상시 이름을 지정")
    void findBeanBySpecificName() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입의 빈 모두 조회")
    void findAllBeanByType() {
        Map<String, MemberRepository> beans = ac.getBeansOfType(MemberRepository.class);
        for (Map.Entry<String, MemberRepository> entry : beans.entrySet()) {
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }
        assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
