package pascal;

import java.util.Arrays;

public class PascalTriangle {
    public static void main(String[] args) {
        int rowCount = 11;
        int[][] result = pascal(rowCount);

//        for (int[] ints : result) {
//            System.out.println(Arrays.toString(ints));
//        }

        int charsPerNum = 6; // Количество символов на одно число
        int fullCharNum = charsPerNum * rowCount; // Количество символов в самой длинной строке
        for (int row = 1; row <= rowCount; row++) {
            int rowCharNum = charsPerNum * row; // Количество символов в строке row
            int shift = (fullCharNum - rowCharNum) / 2; // Пустое место с двух сторон делим пополам

            // В начало добавляем нужное число пробелов
            System.out.print(" ".repeat(shift));

            // Выводим все числа
            for (int i = 1; i <= row; i++) {
                // %-6d -- 6 символов на число, выровнять число по левому краю
                System.out.printf("%-" + charsPerNum + "d", result[row - 1][i - 1]);
            }

            System.out.println();
        }
    }

    public static int[][] pascal(int rowCount) {
        int[][] result = new int[rowCount][];

        for (int row = 0; row < rowCount; row++) {
            result[row] = new int[row + 1];
            result[row][0] = result[row][row] = 1;

            for (int column = 1; column < row; column++) {
                result[row][column] = result[row - 1][column - 1] + result[row - 1][column];
            }
        }

        return result;
    }
}