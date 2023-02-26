package flight;

import java.time.*;

public class FlightTimeCalculator {
    /*
    4)* Рейс из Лос-Анджелеса во Франкфурт отправляется в 15:05 по местному времени и длится 10 ч. 50 м.
    Во сколько он прилетит? Написать метод, который мог бы совершать подобные вычисления.
    */

    public ZonedDateTime getArrivalDateTime(ZonedDateTime departureDateTime, Duration duration, ZoneId requiredZone) {
        return departureDateTime.plus(duration).withZoneSameInstant(requiredZone);
    }

    public static void main(String[] args) {
        FlightTimeCalculator flightTimeCalculator = new FlightTimeCalculator();

        System.out.println(
                flightTimeCalculator.getArrivalDateTime(
                        ZonedDateTime.of(LocalDate.of(2023, 2, 26), LocalTime.of(15, 5), ZoneId.of("America/Los_Angeles")),
                        Duration.ofHours(10).plusMinutes(50),
                        ZoneId.of("Europe/Berlin")
                )
        );
    }
}
