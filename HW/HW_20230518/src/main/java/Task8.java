public class Task8 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {2, 4, 5, 6},
                {3, 5, 6, 7},
                {4, 6, 7, 8}
        };

        System.out.println(isSymmetric(arr));
    }

    // Напишите программу, которая проверяет, является ли двумерный массив симметричным (относительно главной диагонали)
    public static boolean isSymmetric(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i][j] != arr[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
