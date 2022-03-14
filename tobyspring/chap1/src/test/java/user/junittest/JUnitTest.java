package user.junittest;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest {

    private static final Set<JUnitTest> testObjects = new HashSet<>();

    @Test
    void test1() {
        assertThat(testObjects).isNotIn(this);
        testObjects.add(this);
    }

    @Test
    void test2() {
        assertThat(testObjects).isNotIn(this);
        testObjects.add(this);
    }

    @Test
    void test3() {
        assertThat(testObjects).isNotIn(this);
        testObjects.add(this);
    }
}
