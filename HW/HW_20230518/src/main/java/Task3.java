import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3, 2, 1};

        System.out.println(getAvgValue(arr));

        System.out.println(Arrays.toString(reverseArr(arr)));

        System.out.println(isPalindrome(arr));
    }

    // Найти среднее значение всех элементов в массиве
    public static double getAvgValue(int[] arr) {
        return 1.0 * Task1.sumAll(arr) / arr.length;

//        return Arrays.stream(arr).average().orElse(0.0);
    }

    // Поменять порядок элементов массива на обратный

    public static int[] reverseArr(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            res[i] = arr[len - 1 - i];
        }

        return res;
    }

    // Проверить, является ли массив палиндромом (элементы читаются одинаково с начала и с конца)
    public static boolean isPalindrome(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len / 2; i++) {
            if (arr[i] != arr[len - 1 - i]) {
                return false;
            }
        }

        return true;
    }
}

