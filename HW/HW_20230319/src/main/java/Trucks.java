import java.util.concurrent.Exchanger;

public class Trucks {
    /*
    Создать модель поведения с применением Exchanger:

    необходимо доставить посылки двумя грузовиками
    грузовик 1 везет: посылка A -> C и посылка A -> D
    грузовик 2 везет: посылка B -> C и посылка B -> D
 A  _ _ _ _             _ _ _ _ B
           \          /
             - - E - -
 C _ _ _ _ /          \ _ _ _ _ D

    E - точка обмена
    каждый грузовик может отвезти обе посылки, но это не оптимально
    поэтому необходимо создать пункт E для обмена конечными посылками, так, чтобы
    итоговые конечные точки были одинаковые, то есть
    грузовик 1: посылка A -> C и A -> D, после обмена: A -> D и B -> D
    грузовик 2: посылка B -> C и B -> D, после обмена: B -> C и A -> C
     */

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Truck truck1 = new Truck("A", "D", exchanger);
        Truck truck2 = new Truck("B", "C", exchanger);

        truck1.start();
        truck2.start();

        try {
            truck1.join();
            truck2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Truck extends Thread {
        private String paket;
        private String destination;
        private Exchanger<String> exchanger;

        public Truck(String paket, String destination,  Exchanger<String> exchanger) {
            this.paket = paket;
            this.destination = destination;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println("Truck " + Thread.currentThread().getName() + " with package " + paket + " is going to E");
                Thread.sleep(2000);
                String paket2 = exchanger.exchange(paket);
                System.out.println("Truck " + Thread.currentThread().getName() + " with packages " + paket + " and " + paket2 + " is going to " + destination);
                Thread.sleep(2000);
                System.out.println("Truck " + Thread.currentThread().getName() + " with packages " + paket + " and " + paket2 + " has arrived in " + destination);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
