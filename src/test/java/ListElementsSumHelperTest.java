import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListElementsSumHelperTest {
    private static ListElementsSumHelper sumHelper;

    @BeforeClass
    public static void beforeClass() {
        sumHelper = new ListElementsSumHelper();
    }

    @Test
    public void getFilledList_SizeOk() {
        List<Integer> actual = sumHelper.getFilledList(1_000, 1);
        Assert.assertEquals(1_000, actual.size());
    }

    @Test
    public void getFilledList_ContentOk() {
        List<Integer> actual = sumHelper.getFilledList(1_000, 1);
        Assert.assertEquals(Integer.valueOf(1), actual.get(0));
    }

    @Test
    public void getSumUsingExecutorService_Test() {
        List<Integer> list = sumHelper.getFilledList(1_000_000, 1);
        long actual = sumHelper.getSumUsingExecutorService(list);
        Assert.assertEquals(1_000_000, actual);
    }

    @Test
    public void getSumUsingForkJoin_Test() {
        List<Integer> list = sumHelper.getFilledList(1_000_000, 1);
        long actual = sumHelper.getSumUsingForkJoin(list);
        Assert.assertEquals(1_000_000, actual);
    }
}
