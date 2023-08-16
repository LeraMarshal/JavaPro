import java.util.Random;

public class Account {
    public enum Zone {
        ZONE_1, ZONE_2, ZONE_3
    }
    // https://www.baeldung.com/java-random-string

    private static String generateId(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    private String id;
    private boolean status;
    private Zone zone;

    private double balance;


    public Account() {
        this.id = generateId(6);
        this.status = true;
        this.zone = Zone.ZONE_1;
    }

    public Account(boolean status, Zone zone, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException();
        }

        this.id = generateId(6);
        this.status = status;
        this.zone = zone;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public Zone getZone() {
        return zone;
    }

    public double getBalance() {
        return balance;
    }
}
