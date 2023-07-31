import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private Map<Client, Long> clientBalance = new HashMap<>();

    public void add(Client client) {
        clientBalance.put(client, client.getBalance());
    }

    public void printClientsWithCardStatus(Status status) {
        clientBalance.keySet().stream()
                .filter(client -> client.getCard().getStatus().equals(status))
                .forEach(System.out::println);
    }

    public void printGroupingByBalance(long threshold) {
        Map<String, List<Client>> grouped = clientBalance.entrySet().stream().collect(
                Collectors.groupingBy(
                        entry -> entry.getKey().getBalance() > threshold ? "GREATER" : "LESS_OR_EQUAL",
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                )
        );

        System.out.println(grouped);
    }

    public void printAverageBalance() {
        double average = clientBalance.values().stream()
                .mapToLong(num -> num)
                .average()
                .orElse(0);

        System.out.printf("%.2f\n", average);
    }

    public void printClientsWithCardNumbers() {
        for (Client client : clientBalance.keySet()) {
            System.out.printf("%s -> %s\n", client.getName(), client.getCard().getPan());
        }
    }

    public void printClientsWithExpiredCards() {
        YearMonth now = YearMonth.now();

        clientBalance.keySet().stream()
                .filter(client -> client.getCard().isExpired(now))
                .forEach(System.out::println);
    }

    public void printClientsSortedByBalance() {
        clientBalance.entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }

    public void printGroupingByCardExpired() {
        YearMonth now = YearMonth.now();

        Map<Boolean, List<Client>> grouped = clientBalance.keySet().stream()
                .collect(Collectors.groupingBy(client -> client.getCard().isExpired(now)));
        System.out.println(grouped);
    }

    @Override
    public String toString() {
        return "Store{" +
                "clientBalance=" + clientBalance +
                '}';
    }
}
