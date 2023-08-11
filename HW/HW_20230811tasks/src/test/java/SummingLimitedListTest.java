import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SummingLimitedListTest {
    @Test
    void constructorTest() {
        SummingLimitedList list = new SummingLimitedList(0);
        assertEquals(0, list.size());
        assertEquals(0, list.sum());
    }

    @Test
    void constructorExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new SummingLimitedList(-1));
    }

    @Test
    void addingOverLimitTest() {
        SummingLimitedList list = new SummingLimitedList(2);

        list.add(1); // 1
        list.add(2); // 1, 2
        list.add(3); // 1, 2

        assertEquals(2, list.size());
        assertEquals(3, list.sum());
    }

    @Test
    void addingAndRemovalTest() {
        SummingLimitedList list = new SummingLimitedList(3);

        list.add(1); // 1
        list.add(2); // 1, 2
        list.add(3); // 1, 2, 3

        list.remove(1); // 1, 3

        assertEquals(2, list.size());
        assertEquals(4, list.sum());
    }

    @Test
    void removeOutsideBoundsTest() {
        SummingLimitedList list = new SummingLimitedList(1);

        list.add(5); // 5

        list.remove(-1); // 5
        list.remove(1); // 5

        assertEquals(1, list.size());
        assertEquals(5, list.sum());
    }
}
