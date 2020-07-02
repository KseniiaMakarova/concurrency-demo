package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import task.ExecutorServiceTask;

public class ExecutorServiceSumCalculator implements ListElementsSumCalculator {
    private static final int NUMBER_OF_THREADS = 10;

    @Override
    public long getSum(List<Integer> integers) {
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            callableList.add(new ExecutorServiceTask(integers, NUMBER_OF_THREADS, i));
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
}
