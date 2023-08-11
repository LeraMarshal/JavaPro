import java.util.ArrayList;
import java.util.List;

public class SummingLimitedList {
    private List<Integer> numbers;
    private int n;
    private long sum;

    public SummingLimitedList(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        this.n = n;
        this.numbers = new ArrayList<>(n);
    }

    public void add(int i) {
        if (numbers.size() < n) {
            numbers.add(i);
            sum += i;
        }
    }

    public int size() {
        return numbers.size();
    }

    public void remove(int index) {
        if (index >= 0 && index < numbers.size()) {
            sum -= numbers.get(index);
            numbers.remove(index);
        }
    }

    public long sum() {
        return sum;
    }
}
