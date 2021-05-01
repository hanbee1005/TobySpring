package learningtest.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LearningTest {
    Calculator calculator;
    String numFilePath;

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilePath = getClass().getResource("number.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(numFilePath), is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calMultiply(numFilePath), is(24));
    }

    @Test
    public void concatenateOfNumbers() throws IOException {
        assertThat(calculator.concatenate(numFilePath), is("1234"));
    }
}
