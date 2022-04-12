package yoongeonung.basic.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import yoongeonung.basic.member.MemberService;
import yoongeonung.basic.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        //given
        ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
        //when
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //then
        assertThat(memberService).isInstanceOf(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
