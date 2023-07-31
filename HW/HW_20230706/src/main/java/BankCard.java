import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class BankCard {
    private static DateTimeFormatter YEAR_MONTH_FORMAT = DateTimeFormatter.ofPattern("MMyy");

    private Currency currency;
    private Status status;

    private String pan;
    private YearMonth expiration;
    private String cvv;

    public BankCard(String data, Currency currency, Status status) {
        this.pan = data.substring(0, 16);
        this.expiration = YearMonth.parse(data.substring(16, 20), YEAR_MONTH_FORMAT);
        this.cvv = data.substring(20);

        this.currency = currency;
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Status getStatus() {
        return status;
    }

    public String getPan() {
        return pan;
    }

    public boolean isExpired(YearMonth moment) {
        return this.expiration.isBefore(moment);
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "currency=" + currency +
                ", status=" + status +
                ", pan='" + pan + '\'' +
                ", expiration=" + expiration +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
