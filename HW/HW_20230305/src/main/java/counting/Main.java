package counting;

public class Main {
    /*
    Посчитать количество целых чисел в диапазоне от Integer.MINVALUE до Integer.MAXVALUE,
    которые делятся на заданное целое число без остатка.
    Решить данную задачу последовательно и параллельно в нескольких потоках. Сравнить время выполнения.
     */
    public static void main(String[] args) throws Exception {
        int num = 13;

        long startSeq = System.currentTimeMillis();
        int resSeq = countSequential(num);
        long stopSeq = System.currentTimeMillis();

        System.out.printf("countSequential(%d) = %d, elapsedMs = %d\n", num, resSeq, stopSeq - startSeq); // 2737

        long startPar = System.currentTimeMillis();
        int resPar = countParallel(num);
        long stopPar = System.currentTimeMillis();

        System.out.printf("countParallel(%d) = %d, elapsedMs = %d\n", num, resPar, stopPar - startPar); // 1386
    }

    public static int countSequential(int num) {
        int count = 0;

        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            if (i % num == 0) {
                count++;
            }
        }

        return count;
    }

    public static int countParallel(int num) throws InterruptedException {
        CountingTask task1 = new CountingTask(num, Integer.MIN_VALUE, 0);
        CountingTask task2 = new CountingTask(num, 0, Integer.MAX_VALUE);

        task1.start();
        task2.start();

        task1.join();
        task2.join();

        return task1.count + task2.count;
    }

    private static class CountingTask extends Thread {
        private int count = 0;
        private int num;
        private int min;
        private int max;

        public CountingTask(int num, int min, int max) {
            this.num = num;
            this.min = min;
            this.max = max;
        }

        @Override
        public void run() {
            for (int i = min; i < max; i++) {
                if (i % num == 0) {
                    count++;
                }
            }
        }
    }
}
