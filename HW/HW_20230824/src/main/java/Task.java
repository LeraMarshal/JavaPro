public class Task {
    public static void main(String[] args) throws InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        Thread senderThread = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    // отправляем данные в sender
                    sender.sendData(i);
                    // забираем данные из sender и перекладываем в receiver
                    receiver.receiveData(sender.getDataToSend());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread receiverThread = new Thread(() -> {
            try {
                for (int i = 100; i < 200; i++) {
                    // отправляем данные в sender
                    sender.sendData(i);
                    // забираем данные из sender и перекладываем в receiver
                    receiver.receiveData(sender.getDataToSend());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread mainThread = new Thread(() -> {
            try {
                for (int i = 0; i < 200; i++) {
                    // забираем данные из receiver и выводим
                    System.out.println(Thread.currentThread().getName() + " | Received by main thread " + receiver.getReceivedData());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        mainThread.start();
        receiverThread.start();
        senderThread.start();

        senderThread.join();
        receiverThread.join();
        mainThread.join();
    }
}
