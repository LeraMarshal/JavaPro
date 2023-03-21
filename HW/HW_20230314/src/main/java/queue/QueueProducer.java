package queue;

public class QueueProducer extends Thread {

    private ReentrantLockBlockingQueue<String> queue;

    public QueueProducer(ReentrantLockBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put(Thread.currentThread().getName() + ": Message" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
