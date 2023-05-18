public class Task3 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(getAvgValue(arr));

    }

    // Найти среднее значение всех элементов в массиве
    public static double getAvgValue(int[] arr) {
        return (double)Task1.sumAll(arr) / arr.length;

//        return Arrays.stream(arr).average().orElse(0.0);
    }

    // Поменять порядок элементов массива на обратный
    // Проверить, является ли массив палиндромом (элементы читаются одинаково с начала и с конца)
}
