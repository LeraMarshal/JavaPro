package lessons;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessonsGeneratorTest {
    private static LessonsGenerator lessonsGenerator;
    private static ZoneId cetZone;

    @BeforeAll
    public static void init() {
        lessonsGenerator = new LessonsGenerator();
        cetZone = ZoneId.of("CET");
    }

    @Test
    public void getLessons() {
        LocalTime localTime = LocalTime.of(9, 30);

        assertEquals(
                List.of(
                        ZonedDateTime.of(LocalDate.of(2023, 2, 1), localTime, cetZone),
                        ZonedDateTime.of(LocalDate.of(2023, 2, 6), localTime, cetZone),
                        ZonedDateTime.of(LocalDate.of(2023, 2, 8), localTime, cetZone),
                        ZonedDateTime.of(LocalDate.of(2023, 2, 13), localTime, cetZone),
                        ZonedDateTime.of(LocalDate.of(2023, 2, 15), localTime, cetZone),
                        ZonedDateTime.of(LocalDate.of(2023, 2, 20), localTime, cetZone),
                        ZonedDateTime.of(LocalDate.of(2023, 2, 22), localTime, cetZone),
                        ZonedDateTime.of(LocalDate.of(2023, 2, 27), localTime, cetZone)
                ),
                lessonsGenerator.getLessons(2023, 2, 9, 30, cetZone)
        );

        assertThrows(DateTimeException.class, () -> lessonsGenerator.getLessons(2023, 13, 9, 30, cetZone));
        assertThrows(DateTimeException.class, () -> lessonsGenerator.getLessons(2023, 12, 25, 30, cetZone));
        assertThrows(NullPointerException.class, () -> lessonsGenerator.getLessons(2023, 12, 20, 30, null));
    }
}