import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5};

        System.out.println(containsValue(arr, 5));

        sortAsc(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(findIndexOf(arr, 7));
    }

    // Проверить, содержит ли массив заданное значение
    public static boolean containsValue(int[] arr, int value) {
        for (int i : arr) {
            if (i == value) {
                return true;
            }
        }

        return false;

//        return Arrays.asList(arr).contains(value);

//        return Arrays.stream(arr).anyMatch(num -> num == value);
    }


    // Отсортировать массив по возрастанию
    public static void sortAsc(int[] arr) {
//        int tmp = 0;
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }

        }

//        Arrays.sort(arr);
    }

    // Найти индекс первого вхождения заданного значения в массиве
    public static int findIndexOf(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;
    }

}
