package task;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CalculateSumRecursiveTask extends RecursiveTask<Long> {
    private static int threshold;
    private final List<Integer> list;

    public CalculateSumRecursiveTask(List<Integer> list, int numberOfThreads) {
        this.list = list;
        threshold = list.size() / numberOfThreads;
    }

    private CalculateSumRecursiveTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Long compute() {
        if (list.size() > threshold) {
            return ForkJoinTask.invokeAll(createSubTasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        } else {
            return calculate(list);
        }
    }

    private List<CalculateSumRecursiveTask> createSubTasks() {
        return List.of(
                new CalculateSumRecursiveTask(
                        list.subList(0, list.size() / 2 + 1)),
                new CalculateSumRecursiveTask(
                        list.subList(list.size() / 2 + 1, list.size())));
    }

    private long calculate(List<Integer> list) {
        return list.stream().mapToLong(value -> value).sum();
    }
}
