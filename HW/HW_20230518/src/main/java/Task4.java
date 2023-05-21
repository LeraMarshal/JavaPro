import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 1, 2, 3, 4};

        System.out.println(maxSum(arr));

        System.out.println(Arrays.toString(deduplicate(arr)));

        System.out.println(Arrays.toString(findTwoMin(arr)));
    }

    // Найти наибольшую сумму подряд идущих элементов в массиве
    public static int maxSum(int[] arr) {
        int sum = arr[0];
        int maxSum = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] + 1) {
                sum += arr[i];
            } else {
                if (sum > maxSum) {
                    maxSum = sum;
                }

                sum = arr[i];
            }
        }

        if (sum > maxSum) {
            maxSum = sum;
        }

        return maxSum;
    }

    // Удалить все дубликаты из массива
    public static int[] deduplicate(int[] arr) {
        Arrays.sort(arr);

        int[] res = new int[arr.length];
        int pos = 0;
        res[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != res[pos]) {
                res[++pos] = arr[i];
            }
        }

        return Arrays.copyOfRange(res, 0, pos + 1);

//        return Arrays.stream(arr).distinct().toArray();
    }

    // Найти два наименьших значения в массиве
    public static int[] findTwoMin(int[] arr) {
        if (arr.length < 2) {
            return new int[]{arr[0]};
        }

        int[] res = {arr[0], arr[1]};

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < res[0]) {
                res[1] = res[0];
                res[0] = arr[i];
            } else if (arr[i] < res[1]) {
                res[1] = arr[i];
            }
        }

        return res;

//        return Arrays.stream(arr).sorted().limit(2).toArray();
    }
}
