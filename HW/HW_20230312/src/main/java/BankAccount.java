import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    /*
     Переписать класс BankAccount (см. код в репозитории) и его методы:
    - public synchronized void deposit(int amount)
    - public synchronized void withdraw(int amount),
    с применением AtomicInteger и принципа compare and swap,
    так, чтобы несколько потоков могли одновременно безопасно начислять и снимать деньги со счета, не используя при этом синхронизацию.
     */

    private AtomicInteger sum = new AtomicInteger(0);

    public void deposit(int amount) {
//        sum.addAndGet(amount);

        boolean result;
        do {
            int prev = sum.get();
            result = sum.compareAndSet(prev, prev + amount);
        } while (!result);
    }

    public void withdraw(int amount) {
        sum.addAndGet(-amount);
    }

    public int getSum() {
        return sum.get();
    }
}