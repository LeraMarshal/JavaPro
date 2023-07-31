import java.util.*;
import java.util.stream.Collectors;

public class Tasks {
    // Создание контейнера неизменяемых данных
    record Person(String name, int age) {
    }

    public static void main(String[] args) {
        // Дан список чисел. Необходимо отфильтровать только положительные числа и вывести их в отсортированном порядке.
        List<Integer> integerList = List.of(1, 3, 8, 4, -1, 5, 15, 3, 2);

        integerList.stream()
                .filter(num -> num > 0)
                .sorted()
                .forEach(System.out::println);

        //Дан список строк. Необходимо преобразовать каждую строку в верхний регистр, удалить повторяющиеся строки и вывести результат.
        List<String> stringList = List.of("Abc", "Bca", "Kdwkdwld", "Abc", "Def", "abc", "Nodkd", "d");

        stringList.stream()
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::println);

        // Дан список объектов Person. Необходимо отфильтровать только совершеннолетних людей, отсортировать по имени и вывести результат.
        // Person {String name; int age;}
        List<Person> personList = List.of(
                new Person("Name5", 18),
                new Person("Name4", 13),
                new Person("Name3", 21),
                new Person("Name2", 28),
                new Person("Name1", 19)
        );

        personList.stream()
                .filter(person -> person.age >= 18)
                .sorted(Comparator.comparing(person -> person.name))
                .forEach(System.out::println);

        // Дан список чисел. Необходимо найти сумму всех четных чисел.
        Integer sumOfNumbers = integerList.stream()
                .filter(num -> num % 2 == 0)
//                .reduce(Integer::sum)
//                .orElse(-1);
                .mapToInt(num -> num)
                .sum();
        System.out.println(sumOfNumbers);

        // Дан список чисел. Необходимо найти среднее значение всех чисел. //average()
        OptionalDouble average = integerList.stream()
                .mapToInt(num -> num)
                .average();
        System.out.println(average);

        // Дан список слов. Необходимо найти самое длинное слово.
        String maxLengthOfStrings = stringList.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println(maxLengthOfStrings);

        String maxLengthOfStrings1 = stringList.stream()
                .reduce("", (s1, s2) -> s1.length() > s2.length() ? s1 : s2);
        System.out.println(maxLengthOfStrings1);

        // Дан список чисел. Необходимо пропустить первые два элемента и вывести остальные
        integerList.stream()
                .skip(2)
                .forEach(System.out::println);

        // Дан список слов. Необходимо проверить, содержит ли он хотя бы одно слово, начинающееся с буквы "A".
        boolean startsWithA = stringList.stream()
                .anyMatch(s -> s.startsWith("A"));
        System.out.println(startsWithA);

        // Дан список строк. Необходимо объединить их в одну строку, разделенную запятой. //Collectors.joining()
        String joinStrings = stringList.stream()
                .collect(Collectors.joining(", "));
        System.out.println(joinStrings);

        // Дан список объектов Person. Необходимо сгруппировать их по возрасту и вывести результат.
        Map<Integer, List<Person>> groupingByAge = personList.stream()
                .collect(Collectors.groupingBy(person -> person.age));
        System.out.println(groupingByAge);

        // Дан список чисел. Необходимо проверить, являются ли все числа положительными.
        boolean allNumbersArePositive = integerList.stream()
                .allMatch(num -> num > 0);
        System.out.println(allNumbersArePositive);

        // Дан список чисел. Необходимо найти первое четное число.
        Integer findFirstEvenNumb = integerList.stream()
                .dropWhile(num -> num % 2 != 0)
                .findFirst()
                .orElseThrow();
        System.out.println(findFirstEvenNumb);

        // Дан список чисел. Необходимо умножить все числа на заданное значение.
        integerList.stream()
                .map(num -> num * 2)
                .forEach(System.out::println);

        // Дан список строк. Необходимо найти сумму длин всех строк.
        int sumOfLengthOfAllStrings = stringList.stream()
                .mapToInt(String::length)
                .sum();
        System.out.println(sumOfLengthOfAllStrings);

        // Дан список чисел. Необходимо преобразовать его в массив.
        int[] array = integerList.stream()
                .mapToInt(num -> num)
                .toArray();
        System.out.println(Arrays.toString(array));
    }
}
