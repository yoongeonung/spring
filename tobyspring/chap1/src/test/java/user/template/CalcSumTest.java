package user.template;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class CalcSumTest {
    @Test
    void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int num = calculator.calcSum(getClass().getClassLoader().getResource("numbers.txt").getPath());
        assertThat(num).isEqualTo(10);
    }
}
