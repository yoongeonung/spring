package hello.itemservice.messages;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    @DisplayName("メッセージテスト")
    void helloMessage() {
        //given
        String result = messageSource.getMessage("hello", null, null);
        //when

        //then
        assertThat(result).isEqualTo("안녕");
    }
}
