import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ListElementsSumHelper {
    public static final int NUMBER_OF_THREADS = 10;

    public List<Integer> getFilledList(int size, int value) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(value);
        }
        return list;
    }

    public long getSumUsingExecutorService(List<Integer> integerList) {
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            callableList.add(new ExecutorServiceTask(integerList, NUMBER_OF_THREADS, i));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        long sum = 0;
        try {
            List<Future<Integer>> futures = executorService.invokeAll(callableList);
            executorService.shutdown();
            for (Future<Integer> future : futures) {
                sum += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    public long getSumUsingForkJoin(List<Integer> list) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        return pool.invoke(new CalculateSumRecursiveTask(list, NUMBER_OF_THREADS));
    }
}
