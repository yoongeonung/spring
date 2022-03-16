package user.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class CalcSumTest {
    private Calculator calculator; // fixture
    private String numFilePath; // fixture

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
        numFilePath = getClass().getClassLoader().getResource("numbers.txt").getPath();
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calMultiply(numFilePath)).isEqualTo(24);
    }
    @Test
    void sumOfNumbers() throws IOException {
        assertThat((int) calculator.calcSum(numFilePath)).isEqualTo(10);
    }

    @Test
    void concatOfNumbers() throws IOException {
        assertThat(calculator.concatenate(numFilePath)).isEqualTo("1234");
    }
}
