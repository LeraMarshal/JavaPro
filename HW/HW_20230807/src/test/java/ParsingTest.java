import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParsingTest {

    @Test
    void findProductsAbove() {
        String context = """
                ABC123:5
                XYZ789:12
                DEF456:8
                JKL012:15
                MNO345:6
                """;

        Map<String, Integer> expected = Map.of(
                "XYZ789", 12,
                "JKL012", 15
        );

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(context.getBytes());

        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(expected, Parsing.findProductsAbove(byteArrayInputStream, 10));
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            Parsing.findProductsAbove(null, 10);
        });
    }
}
