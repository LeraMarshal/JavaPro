public class Sender {
    private volatile int dataToSend = -1;

    public synchronized void sendData(int data) throws InterruptedException {
        // ждем, пока данных не будет
        while (this.dataToSend >= 0) {
            this.wait();
        }

        System.out.println(Thread.currentThread().getName() + " | Sent to sender " + data);

        // выставляем данные
        this.dataToSend = data;
        // пробуждаем потоки
        this.notifyAll();
    }

    public synchronized int getDataToSend() throws InterruptedException {
        int result;

        // ждем, пока данные появятся
        while (this.dataToSend < 0) {
            this.wait();
        }

        // запоминаем
        result = this.dataToSend;
        // выставляем, что теперь данных нет
        this.dataToSend = -1;
        // пробуждаем потоки
        this.notifyAll();

        System.out.println(Thread.currentThread().getName() + " | Received from sender " + result);

        return result;
    }
}
