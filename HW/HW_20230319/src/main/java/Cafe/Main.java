package Cafe;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            Restaurant restaurant = new Restaurant();

            List<Client> clients = IntStream.range(1, 11)
                    .mapToObj(i -> new Client("Client " + i, restaurant))
                    .peek(Client::start)
                    .collect(Collectors.toList());

            for (Client client : clients) {
                client.join();
            }

            restaurant.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
