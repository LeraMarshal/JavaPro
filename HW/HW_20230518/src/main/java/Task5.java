import java.util.Arrays;

public class Task5 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 7, 9, 11, 12, 19, 21, 22, 23, 24};
        int[] arr2 = {1, 2, 3, 4, 5, 6};

        System.out.println(Arrays.toString(findMaxSequence(arr)));
        System.out.println(findMaxSumEven(arr2));
    }

    // Найти наибольшую возрастающую последовательность в массиве (элементы идут по порядку)
    public static int[] findMaxSequence(int[] arr) {
        int maxLeft = 0;
        int maxRight = 1;
        int left = 0;
        int right = 1;

        for (; right < arr.length; right++) {
            if (arr[right] != arr[right - 1] + 1) {
                if ((right - left) > (maxRight - maxLeft)) {
                    maxRight = right;
                    maxLeft = left;
                }

                left = right;
            }
        }

        if ((right - left) > (maxRight - maxLeft)) {
            maxRight = right;
            maxLeft = left;
        }

        return Arrays.copyOfRange(arr, maxLeft, maxRight);

    }

    // Найти наибольшую сумму подмассива с нечетным количеством элементов
    public static int findMaxSumEven(int[] arr){
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int size = 1; i + size <= arr.length; size += 2) {
                int sum = 0;

                for (int j = i; j < i + size; j++) {
                    sum += arr[j];
                }

                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }
}
