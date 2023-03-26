package buildings;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Phaser;

public class Brigade implements Runnable {
    private Phaser phaser;
    private BlockingQueue<String> queue;
    private long duration;

    private volatile int resources = 0;

    public Brigade(Phaser phaser, BlockingQueue<String> queue, long duration) {
        this.phaser = phaser;
        this.queue = queue;
        this.duration = duration;
    }

    @Override
    public void run() {
        int lastPhase = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String task = queue.take();

                System.out.println(Thread.currentThread().getName() + " is building " + task);
                Thread.sleep(duration);

                int nextPhase = phaser.arrive();
                if (nextPhase != lastPhase) {
                    resources = 0;
                    lastPhase = nextPhase;
                }
                resources++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getResources() {
        return resources;
    }
}

