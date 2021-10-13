package jp.ac.hal.yoongeonung.spring_mvc;

import jp.ac.hal.yoongeonung.spring_mvc.repository.MemberRepository;
import jp.ac.hal.yoongeonung.spring_mvc.repository.MemoryMemberRepository;
import jp.ac.hal.yoongeonung.spring_mvc.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
