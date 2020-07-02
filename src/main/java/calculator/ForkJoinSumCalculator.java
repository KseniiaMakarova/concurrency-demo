package calculator;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import task.CalculateSumRecursiveTask;

public class ForkJoinSumCalculator implements ListElementsSumCalculator {
    private static final int NUMBER_OF_THREADS = 10;

    @Override
    public long getSum(List<Integer> integers) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        return pool.invoke(new CalculateSumRecursiveTask(integers, NUMBER_OF_THREADS));
    }
}
