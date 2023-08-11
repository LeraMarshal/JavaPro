import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SummingLimitedListTest {
    @Test
    void constructorTest(){
        SummingLimitedList list = new SummingLimitedList(0);
        Assertions.assertEquals(0, list.size());
        Assertions.assertEquals(0, list.sum());

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SummingLimitedList(-1));
    }

    @Test
    void internalsTest() {
        SummingLimitedList list = new SummingLimitedList(2);

        list.add(1); // 1
        list.add(2); // 1, 2
        list.add(3); // 1, 2

        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(3, list.sum());

        list.remove(-1); // 1, 2
        list.remove(2); // 1, 2

        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(3, list.sum());

        list.remove(0); // 2

        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(2, list.sum());
    }
}