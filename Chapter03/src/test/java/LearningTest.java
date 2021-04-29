import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LearningTest {

    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(getClass().getResource("number.txt").getPath());
        assertThat(sum, is(10));
    }

    private class Calculator {
        public Integer calcSum(String filepath) throws IOException {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(filepath));
                Integer sum = 0;
                String line = null;
                while ((line = br.readLine()) != null) {
                    sum += Integer.valueOf(line);
                }

                return sum;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw e;
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
