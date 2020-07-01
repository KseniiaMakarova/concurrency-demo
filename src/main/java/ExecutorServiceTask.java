import java.util.List;
import java.util.concurrent.Callable;

public class ExecutorServiceTask implements Callable<Integer> {
    private final List<Integer> list;
    private final int delimiter;
    private final int size;

    public ExecutorServiceTask(List<Integer> list, int numberOfThreads, int delimiter) {
        this.list = list;
        this.delimiter = delimiter;
        size = list.size() / numberOfThreads;
    }

    @Override
    public Integer call() {
        int startingPoint = delimiter * size;
        int endPoint = startingPoint + size;
        List<Integer> sublist = list.subList(startingPoint, endPoint);
        return sublist.stream().mapToInt(value -> value).sum();
    }
}
