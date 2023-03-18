package queue;

public class Main {
    /*
    BlockingQueue:
    Класс Продьюсер - генерирует сообщения в очередь
    Класс Консьюмер - забирает сообщения из очереди

    Задача - реализовать класс Consumer, который будет анализировать сообщения в очереди и при входящем сообщении "exit" заканчивать работу.
    При реализации можно использовать класс MyBlockingQueue или ReentrantLockBlockingQueue в репозитории
    или одну из стандартных реализаций BlockingQueue из библиотеки.
     */
    public static void main(String[] args) {
        ReentrantLockBlockingQueue<String> reentrantLockBlockingQueue = new ReentrantLockBlockingQueue<>(5);
        QueueProducer producer = new QueueProducer(reentrantLockBlockingQueue);
        QueueConsumer consumer = new QueueConsumer(reentrantLockBlockingQueue);

        producer.start();
        consumer.start();

        try {
            producer.join();
            reentrantLockBlockingQueue.put(QueueConsumer.EXIT);
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
