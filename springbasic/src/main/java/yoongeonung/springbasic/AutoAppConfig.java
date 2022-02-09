package yoongeonung.springbasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {


    /**
     * 스프링 부트와 함께 사용시 수동빈과 자동빈이 같은 이름으로 등록되어있을시
     * 오류가 난다.
     * 스프링 부트가 아닐시에는 수동빈을 우선한다.
     */

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
