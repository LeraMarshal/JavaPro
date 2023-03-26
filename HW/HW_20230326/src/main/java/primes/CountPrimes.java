package primes;

import java.util.List;
import java.util.concurrent.*;

public class CountPrimes {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            countPrimesParallel();
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void countPrimesParallel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(); // Без ограничения числа потоков в пуле

        List<Task> tasks = List.of(
                new Task(2, 250_000),
                new Task(250_000, 400_000),
                new Task(400_000, 500_000)
        );

        // Список Future, которые нам дает executor при submit
        // Future -- "обещание" какого-то результата в будущем
        List<Future<Integer>> futures = tasks.stream().map(executorService::submit).toList();

        int sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get(); // Ожидает, когда обещание будет выполнено, и забирает результат, добавляя его к sum
        }

        System.out.println("Total number of primes: " + sum);

        executorService.shutdown();
    }

    static class Task implements Callable<Integer> {
        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int start;
        int end;

        @Override
        public Integer call() {
            int count = 0;

            for (int i = start; i < end; i++) {
                boolean isPrime = true;

                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) count++;
            }

            return count;
        }
    }
}