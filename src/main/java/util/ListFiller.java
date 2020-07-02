package util;

import java.util.ArrayList;
import java.util.List;

public class ListFiller {
    public List<Integer> getFilledList(int size, int value) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(value);
        }
        return list;
    }
}
