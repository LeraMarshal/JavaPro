package queue;

public class QueueConsumer extends Thread {
    public static final String EXIT = "exit";
    private ReentrantLockBlockingQueue<String> queue;

    public QueueConsumer(ReentrantLockBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = queue.take();
                if (message.equalsIgnoreCase(EXIT)) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " received message: " + message +
                        " Elements in queue:" + queue.getSize());
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
