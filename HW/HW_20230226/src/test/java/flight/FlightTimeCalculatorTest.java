package flight;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class FlightTimeCalculatorTest {
    private static FlightTimeCalculator flightTimeCalculator;

    @BeforeAll
    public static void init() {
        flightTimeCalculator = new FlightTimeCalculator();
    }

    @Test
    public void getArrivalDateTime() {
        ZonedDateTime departure = ZonedDateTime.of(
                LocalDate.of(2023, 2, 26),
                LocalTime.of(15, 5),
                ZoneId.of("America/Los_Angeles")
        );

        Duration duration = Duration.ofHours(10).plusMinutes(50);

        ZoneId destinationZone = ZoneId.of("Europe/Berlin");

        assertEquals(
                ZonedDateTime.of(LocalDate.of(2023, 2, 27), LocalTime.of(10, 55), ZoneId.of("Europe/Berlin")),
                flightTimeCalculator.getArrivalDateTime(departure, duration, destinationZone)
        );

        assertThrows(NullPointerException.class, () -> flightTimeCalculator.getArrivalDateTime(departure, duration, null));
        assertThrows(NullPointerException.class, () -> flightTimeCalculator.getArrivalDateTime(departure, null, destinationZone));
        assertThrows(NullPointerException.class, () -> flightTimeCalculator.getArrivalDateTime(null, duration, destinationZone));
        assertThrows(NullPointerException.class, () -> flightTimeCalculator.getArrivalDateTime(null, null, destinationZone));
        assertThrows(NullPointerException.class, () -> flightTimeCalculator.getArrivalDateTime(null, duration, null));
        assertThrows(NullPointerException.class, () -> flightTimeCalculator.getArrivalDateTime(departure, null, null));
        assertThrows(NullPointerException.class, () -> flightTimeCalculator.getArrivalDateTime(null, null, null));
    }
}