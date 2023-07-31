import java.util.Random;
import java.util.stream.Collectors;

public class BankCardDataGenerator {
    private Random random;

    public BankCardDataGenerator(Random random) {
        this.random = random;
    }

    public String generate() {
        String pan = random.ints(16, 0, 10)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
        String dateCVV = String.format(
                "%02d%02d%03d",
                random.nextInt(1, 13),
                random.nextInt(21, 30),
                random.nextInt(1, 1000)
        );
        return pan + dateCVV;
    }

}
