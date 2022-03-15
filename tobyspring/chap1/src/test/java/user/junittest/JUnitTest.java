package user.junittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration // 같은 구역안에 location 있으면 생략 가능
public class JUnitTest {

    @Autowired
    ApplicationContext context;

    private static ApplicationContext contextObj = null;
    private static final Set<JUnitTest> testObjects = new HashSet<>();

    @Test
    void test1() {
        assertThat(testObjects).isNotIn(this);
        testObjects.add(this);

        assertThat(contextObj == null || contextObj == this.context).isTrue();
        contextObj = this.context;
    }

    @Test
    void test2() {
        assertThat(testObjects).isNotIn(this);
        testObjects.add(this);

        assertThat(contextObj == null || contextObj == this.context).isTrue();
        contextObj = this.context;
    }

    @Test
    void test3() {
        assertThat(testObjects).isNotIn(this);
        testObjects.add(this);
        assertThat(contextObj).isNotNull().isSameAs(this.context);
        contextObj = this.context;
    }
}
