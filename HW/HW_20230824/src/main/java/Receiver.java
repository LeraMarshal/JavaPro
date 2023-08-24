public class Receiver {
    // -1 мы считаем, что данных нет
    private volatile int receivedData = -1;

    public synchronized void receiveData(int data) throws InterruptedException {
        // ждем, пока данных не будет
        while (this.receivedData >= 0) {
            this.wait();
        }

        System.out.println(Thread.currentThread().getName() + " | Received by receiver " + data);

        // выставляем данные
        this.receivedData = data;

        // пробуждаем потоки
        this.notifyAll();
    }

    public synchronized int getReceivedData() throws InterruptedException {
        int result;

        // ждем, пока данные появятся
        while (this.receivedData < 0) {
            this.wait();
        }

        // запоминаем
        result = this.receivedData;
        // выставляем, что теперь данных нет
        this.receivedData = -1;
        // пробуждаем потоки
        this.notifyAll();

        System.out.println(Thread.currentThread().getName() + " | Got from receiver " + result);

        return result;
    }
}
