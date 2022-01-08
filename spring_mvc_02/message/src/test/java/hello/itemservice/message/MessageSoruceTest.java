package hello.itemservice.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSoruceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    @DisplayName("Default 설정 확인 테스트")
    void helloDefaultMessage() {
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("hello");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundDefaultMessageCode() {
        String result = messageSource.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        String result = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("hello Spring");
    }

    @Test
    void koLang() {
        String result = messageSource.getMessage("hello", null, Locale.KOREA);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void jaLang() {
        String result = messageSource.getMessage("hello", null, Locale.JAPAN);
        assertThat(result).isEqualTo("こんにちは");
    }
}
