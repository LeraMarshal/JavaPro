package lessons;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class LessonsGenerator {
    /*
    3) Составить список времен начала всех занятий по Java на февраль, если предположить,
    что они будут проходить каждый понедельник/среду c 9:30 CET.
    */

    public List<ZonedDateTime> getLessons(int year, int month, int hour, int minute, ZoneId zoneId) {
        List<ZonedDateTime> lessons = new ArrayList<>();

        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate currentDate = startOfMonth;

        LocalTime requiredTime = LocalTime.of(hour, minute);

        int days = startOfMonth.lengthOfMonth();
        for (int i = 0; i < days; i++) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.WEDNESDAY) {
                lessons.add(ZonedDateTime.of(currentDate, requiredTime, zoneId));
            }

            currentDate = currentDate.plusDays(1L);
        }

        return lessons;
    }

    public static void main(String[] args) {
        LessonsGenerator lessonsGenerator = new LessonsGenerator();
        System.out.println(lessonsGenerator.getLessons(2023, 2, 9, 30, ZoneId.of("CET")));
    }
}
