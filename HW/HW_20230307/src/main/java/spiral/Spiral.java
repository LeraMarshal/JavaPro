package spiral;

import java.util.Arrays;

public class Spiral {
    private enum Direction {
        TOP, RIGHT, BOTTOM, LEFT
    }

    /*
    Дано одно значение.
    Оно является длиной и высотой двойного массива.
    Нужно заполнить массив по спирали от левого верхнего угла до центра массива.
     */
    public static void main(String[] args) {
        for (int[] ints : spiralMatrixFill(6)) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static int[][] spiralMatrixFill(int n) {
        int[][] points = new int[n][n];

//        spiralMatrixFillRec(n, points, 0, 0, Direction.RIGHT, 1);
        spiralMatrixFillNonRec(n, points, 0, 0, Direction.RIGHT, 1);

        return points;
    }

    // Рекурсивный вариант
    private static void spiralMatrixFillRec(int n, int[][] points, int row, int column, Direction direction, int value) {
        points[row][column] = value;

        switch (direction) {
            // Вправо или вниз
            case RIGHT:
                if (column + 1 < n && points[row][column + 1] == 0) {
                    spiralMatrixFillRec(n, points, row, column + 1, Direction.RIGHT, value + 1);
                } else if (row + 1 < n && points[row + 1][column] == 0) {
                    spiralMatrixFillRec(n, points, row + 1, column, Direction.BOTTOM, value + 1);
                }
                break;

            // Вниз или влево
            case BOTTOM:
                if (row + 1 < n && points[row + 1][column] == 0) {
                    spiralMatrixFillRec(n, points, row + 1, column, Direction.BOTTOM, value + 1);
                } else if (column - 1 >= 0 && points[row][column - 1] == 0) {
                    spiralMatrixFillRec(n, points, row, column - 1, Direction.LEFT, value + 1);
                }
                break;

            // Влево или вверх
            case LEFT:
                if (column - 1 >= 0 && points[row][column - 1] == 0) {
                    spiralMatrixFillRec(n, points, row, column - 1, Direction.LEFT, value + 1);
                } else if (row - 1 >= 0 && points[row - 1][column] == 0) {
                    spiralMatrixFillRec(n, points, row - 1, column, Direction.TOP, value + 1);
                }
                break;

            // Вверх или вправо
            case TOP:
                if (row - 1 >= 0 && points[row - 1][column] == 0) {
                    spiralMatrixFillRec(n, points, row - 1, column, Direction.TOP, value + 1);
                } else if (column + 1 < n && points[row][column + 1] == 0) {
                    spiralMatrixFillRec(n, points, row, column + 1, Direction.RIGHT, value + 1);
                }
                break;
        }
    }

    // Нерекурсивный вариант
    private static void spiralMatrixFillNonRec(int n, int[][] points, int row, int column, Direction direction, int value) {
        boolean finished = false;

        while (!finished) {
            points[row][column] = value++;

            switch (direction) {
                // Вправо или вниз
                case RIGHT:
                    if (column + 1 < n && points[row][column + 1] == 0) {
                        column += 1;
                    } else if (row + 1 < n && points[row + 1][column] == 0) {
                        direction = Direction.BOTTOM;
                        row += 1;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;

                // Вниз или влево
                case BOTTOM:
                    if (row + 1 < n && points[row + 1][column] == 0) {
                        row += 1;
                    } else if (column - 1 >= 0 && points[row][column - 1] == 0) {
                        direction = Direction.LEFT;
                        column -= 1;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;

                // Влево или вверх
                case LEFT:
                    if (column - 1 >= 0 && points[row][column - 1] == 0) {
                        column -= 1;
                    } else if (row - 1 >= 0 && points[row - 1][column] == 0) {
                        direction = Direction.TOP;
                        row -= 1;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;

                // Вверх или вправо
                case TOP:
                    if (row - 1 >= 0 && points[row - 1][column] == 0) {
                        row -= 1;
                    } else if (column + 1 < n && points[row][column + 1] == 0) {
                        direction = Direction.RIGHT;
                        column += 1;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;
            }
        }
    }
}

