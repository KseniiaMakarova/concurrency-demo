package util;

import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListFillerTest {
    private static ListFiller listFiller;

    @BeforeClass
    public static void beforeClass() {
        listFiller = new ListFiller();
    }

    @Test
    public void getFilledList_SizeOk() {
        List<Integer> actual = listFiller.getFilledList(1_000, 1);
        Assert.assertEquals(1_000, actual.size());
    }

    @Test
    public void getFilledList_ContentOk() {
        List<Integer> actual = listFiller.getFilledList(1_000, 1);
        Assert.assertEquals(Integer.valueOf(1), actual.get(0));
    }
}
