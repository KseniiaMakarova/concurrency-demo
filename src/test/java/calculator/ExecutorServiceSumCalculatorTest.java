package calculator;

import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ListFiller;

public class ExecutorServiceSumCalculatorTest {
    private static ListFiller listFiller;
    private static ListElementsSumCalculator calculator;

    @BeforeClass
    public static void beforeClass() {
        listFiller = new ListFiller();
        calculator = new ExecutorServiceSumCalculator();
    }

    @Test
    public void getSum_Test() {
        List<Integer> list = listFiller.getFilledList(1_000_000, 1);
        long actual = calculator.getSum(list);
        Assert.assertEquals(1_000_000, actual);
    }
}
