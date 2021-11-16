package jp.ac.hal.yoongeonung.springboot.scan;

import jp.ac.hal.yoongeonung.springboot.AutoAppConfig;
import jp.ac.hal.yoongeonung.springboot.discount.DiscountPolicy;
import jp.ac.hal.yoongeonung.springboot.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    @DisplayName("컴포넌트 스캔 테스트")
    void basicScan() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        //when
        //then
    }
}
