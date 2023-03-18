package queue;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
      Переписать класс ReentrantLockBlockingQueue (См. код в репозитории) так, чтобы

    - очередь была на основе массива (ArrayDeque)
    - размер очереди задавался через пареметр size в конструкторе
    - при попытке положить элемент в заполненную очередь, поток-продьюсер ожидал бы освобождение места
 */
public class ReentrantLockBlockingQueue<T> {
    private int capacity; // Задаем вместимость
    private Queue<T> queue;
    private Lock lock = new ReentrantLock(true);
    private Condition isNotEmpty = lock.newCondition(); // условие "очередь не пуста"
    private Condition isNotFull = lock.newCondition(); // условие "очередь не заполнена"

    public ReentrantLockBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= capacity) { // Ожидаем пока не появится место в очереди
                isNotFull.await();
            }
            queue.add(item);
            isNotEmpty.signal(); // Сигнал о том, что очередь не пуста
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) { // Ожидаем добавление элемента в очередь
                isNotEmpty.await();
            }
            isNotFull.signal(); // Сигнал о появлении места в очереди
            return queue.poll();
        } finally {
            lock.unlock();
        }

    }

    public int getSize() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}