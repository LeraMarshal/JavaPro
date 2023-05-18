public class Task1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};

        System.out.println(sumAll(arr));
        System.out.println(maxInt(arr));
        System.out.println(countEvenNumbers(arr));
    }

    //1. Найти сумму всех элементов в массиве
    public static int sumAll(int[] arr) {
        int sum = 0;

        for (int i : arr) {
            sum += i;
        }

//        int sum = Arrays.stream(arr).sum();

        return sum;
    }

    //2. Найти наибольшее значение в массиве
    public static int maxInt(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

//        Arrays.sort(arr);
//        int max = arr[arr.length - 1];

//        int max = Arrays.stream(arr).max().getAsInt();

        return max;
    }

    //3. Посчитать количество четных чисел в массиве
    public static int countEvenNumbers(int[] arr) {
        int count = 0;

        for (int i : arr) {
            if (i % 2 == 0) {
                count++;
            }
        }

//        long count = Arrays.stream(arr)
//                .filter(num -> num % 2 == 0)
//                .count();

        return count;
    }
}
