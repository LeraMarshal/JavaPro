import java.util.Arrays;
import java.util.Stack;

// https://e-maxx.ru/algo/maximum_zero_submatrix -> реализован данный алгоритм

public class Task6 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 0, 0, 1, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        int[][] res = findMaxSubMatrix(arr);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Напишите программу, которая находит наибольшую общую подматрицу, состоящую только из нулей, в двумерном массиве
    public static int[][] findMaxSubMatrix(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int maxW = 0;
        int maxH = 0;

        int[] d = new int[m];
        Arrays.fill(d, -1);

        int[] d1 = new int[m];
        int[] d2 = new int[m];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] != 0) {
                    d[j] = i;
                }
            }

            while (!st.isEmpty()) {
                st.pop();
            }

            for (int j = 0; j < m; ++j) {
                while (!st.empty() && d[st.peek()] <= d[j]) st.pop();
                d1[j] = st.empty() ? -1 : st.peek();
                st.push(j);
            }

            while (!st.empty()) st.pop();

            for (int j = m - 1; j >= 0; --j) {
                while (!st.empty() && d[st.peek()] <= d[j]) st.pop();
                d2[j] = st.empty() ? m : st.peek();
                st.push(j);
            }

            for (int j = 0; j < m; ++j) {
                int w = i - d[j];
                int h = d2[j] - d1[j] - 1;

                if (w * h > maxW * maxH) {
                    maxW = w;
                    maxH = h;
                }
            }
        }

        return new int[maxH][maxW];
    }
}
