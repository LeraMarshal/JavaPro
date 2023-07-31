public class Client {
    private String name;
    private BankCard card;
    private long balance;

    public Client(String name, BankCard card, long balance) {
        this.name = name;
        this.card = card;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BankCard getCard() {
        return card;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", card=" + card +
                ", balance=" + balance +
                '}';
    }
}
