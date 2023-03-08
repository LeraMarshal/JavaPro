import java.util.LinkedHashMap;
import java.util.Map;

public class ArabicToRomanConverter {
    // LinkedHashMap, чтобы идти от больших чисел к меньшим
    private static LinkedHashMap<String, Long> MAPPING = new LinkedHashMap<>();

    static {
        // ctrl+cmd+space u+0305 -- overline
        // https://www.compart.com/en/unicode/U+0305
        // добавляем сначала букву, потом добавляем символ overline
        // есть еще double overline - u+033f

        MAPPING.put("M̅", 1000000L);
        MAPPING.put("C̅M̅", 900000L);
        MAPPING.put("D̅", 500000L);
        MAPPING.put("C̅D̅", 400000L);
        MAPPING.put("C̅", 100000L);
        MAPPING.put("X̅C̅", 90000L);
        MAPPING.put("L̅", 50000L);
        MAPPING.put("X̅L̅", 40000L);
        MAPPING.put("X̅", 10000L);
        MAPPING.put("MX̅", 9000L);
        MAPPING.put("V̅", 5000L);
        MAPPING.put("MV̅", 4000L);
        MAPPING.put("M", 1000L);
        MAPPING.put("CM", 900L);
        MAPPING.put("D", 500L);
        MAPPING.put("CD", 400L);
        MAPPING.put("C", 100L);
        MAPPING.put("XC", 90L);
        MAPPING.put("L", 50L);
        MAPPING.put("XL", 40L);
        MAPPING.put("X", 10L);
        MAPPING.put("IX", 9L);
        MAPPING.put("V", 5L);
        MAPPING.put("IV", 4L);
        MAPPING.put("I", 1L);
    }

    public static String arabicToRoman(long number) {
        StringBuilder builder = new StringBuilder();

        // Для случая с отрицательным числом
//        if (number < 0L) {
//            builder.append("-");
//            number *= -1L;
//        }

        // У римлян не было отрицательных чисел и нуля
        if (number <= 0L) {
            throw new IllegalArgumentException("No negative number or zero allowed");
        }

        // Идем по числам, начиная с самого большого
        // У нас пары Римская (ключ) -> Арабская (значение)
        for (Map.Entry<String, Long> entry : MAPPING.entrySet()) {
            // Сохраним значение, чтобы дважды не делать entry.getValue()
            long value = entry.getValue();

            // Если мы можем отнять текущее арабское число от нашего, делаем это (добавляя римскую цифру),
            // и делаем это до тех пор, пока можем его отнять
            while (number >= value) {
                number -= value;

                builder.append(entry.getKey());
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
//        System.out.println(arabicToRoman(-1L)); // -I
        System.out.println(arabicToRoman(1L)); // I

//        System.out.println(arabicToRoman(-6L)); // -VI
        System.out.println(arabicToRoman(6L)); // VI

//        System.out.println(arabicToRoman(-123L)); // -CXXIII
        System.out.println(arabicToRoman(123L)); // CXXIII

//        System.out.println(arabicToRoman(-1234L)); // -MCCXXXIV
        System.out.println(arabicToRoman(1234L)); // MCCXXXIV

//        System.out.println(arabicToRoman(-12345L)); // -X̅MMCCCXLV
        System.out.println(arabicToRoman(12345L)); // X̅MMCCCXLV

//        System.out.println(arabicToRoman(-123456L)); // -C̅X̅X̅MMMCDLVI
        System.out.println(arabicToRoman(123456L)); // C̅X̅X̅MMMCDLVI

//        System.out.println(arabicToRoman(-1234567L)); // -M̅C̅C̅X̅X̅X̅MV̅DLXVII
        System.out.println(arabicToRoman(1234567L)); // M̅C̅C̅X̅X̅X̅MV̅DLXVII

//        System.out.println(arabicToRoman(-324576L)); // -C̅C̅C̅X̅X̅MV̅DLXXVI
        System.out.println(arabicToRoman(324576L)); // C̅C̅C̅X̅X̅MV̅DLXXVI
    }
}
