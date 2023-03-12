package diagonal;

import java.util.Random;

public class Diagonal {
    private enum Direction {
        TOP_RIGHT, BOTTOM_RIGHT, TOP_LEFT, BOTTOM_LEFT
    }

    private static final Random RANDOM = new Random();

    /*
    Задать двумерный массив двумя значениями.
    Взять рандомный элемент в этом массиве и начинать заполнять его по диагонали.
    Дойдя до края, он должен отскочить и начать заполнять другую диагональ.
    Если попадает в угол, взять другую рандомную точку и заполнять от неё по тому же принципу.
     */
    public static void main(String[] args) {
        for (int[] ints : diagonalMatrixFill(10, 10)) {
            for (int anInt : ints) {
                System.out.printf("%3d", anInt);
            }
            System.out.println();
        }
    }

    public static int[][] diagonalMatrixFill(int rowCount, int columnCount) {
        int[][] points = new int[rowCount][columnCount];

        int filled = 0; // счетчик заполненных точек
        while (filled != rowCount * columnCount) {
            // Пока не заполним весь массив, будем пробовать заполнять по диагоналям, начиная со случайного элемента

            int startRow = RANDOM.nextInt(rowCount);
            int startColumn = RANDOM.nextInt(columnCount);

            filled += diagonalMatrixFillNonRec(rowCount, columnCount, points, startRow, startColumn, Direction.TOP_RIGHT, filled + 1);
        }

        return points;
    }

    // Рекурсивный вариант
    // Возвращаем количество заполненных точек
    private static int diagonalMatrixFillRec(int rowCount, int columnCount, int[][] points, int row, int column, Direction direction, int value) {
        int filled = 0; // количество заполненных этим вызовом точек

        // Если значения на точке нет -- записываем его
        if (points[row][column] == 0) {
            points[row][column] = value++;
            filled = 1;
        }

        // Если пришли в угол -- выходим
        if ((row == 0 && column == 0) || (row == 0 && column == columnCount - 1) || (row == rowCount - 1 && column == 0) || (row == rowCount - 1 && column == columnCount - 1)) {
            return filled;
        }

        switch (direction) {
            // Идем вверх-вправо, вниз-вправо, вверх-влево (куда сможем раньше)
            case TOP_RIGHT:
                if (row - 1 >= 0 && column + 1 < columnCount && points[row - 1][column + 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row - 1, column + 1, Direction.TOP_RIGHT, value);
                } else if (row + 1 < rowCount && column + 1 < columnCount && points[row + 1][column + 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row + 1, column + 1, Direction.BOTTOM_RIGHT, value);
                } else if (row - 1 >= 0 && column - 1 >= 0 && points[row - 1][column - 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row - 1, column - 1, Direction.TOP_LEFT, value);
                }
                break;

            // Идем вниз-вправо, вверх-вправо, вниз-влево (куда сможем раньше)
            case BOTTOM_RIGHT:
                if (row + 1 < rowCount && column + 1 < columnCount && points[row + 1][column + 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row + 1, column + 1, Direction.BOTTOM_RIGHT, value);
                } else if (row - 1 >= 0 && column + 1 < columnCount && points[row - 1][column + 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row - 1, column + 1, Direction.TOP_RIGHT, value);
                } else if (row + 1 < rowCount && column - 1 >= 0 && points[row + 1][column - 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row + 1, column - 1, Direction.BOTTOM_LEFT, value);
                }
                break;

            // Идем вверх-влево, вверх-вправо, вниз-влево (куда сможем раньше)
            case TOP_LEFT:
                if (row - 1 >= 0 && column - 1 >= 0 && points[row - 1][column - 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row - 1, column - 1, Direction.TOP_LEFT, value);
                } else if (row - 1 >= 0 && column + 1 < columnCount && points[row - 1][column + 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row - 1, column + 1, Direction.TOP_RIGHT, value);
                } else if (row + 1 < rowCount && column - 1 >= 0 && points[row + 1][column - 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row + 1, column - 1, Direction.BOTTOM_LEFT, value);
                }
                break;

            // Идем вниз-влево, вниз-вправо, вверх-влево (куда сможем раньше)
            case BOTTOM_LEFT:
                if (row + 1 < rowCount && column - 1 >= 0 && points[row + 1][column - 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row + 1, column - 1, Direction.BOTTOM_LEFT, value);
                } else if (row + 1 < rowCount && column + 1 < columnCount && points[row + 1][column + 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row + 1, column + 1, Direction.BOTTOM_RIGHT, value);
                } else if (row - 1 >= 0 && column - 1 >= 0 && points[row - 1][column - 1] == 0) {
                    filled += diagonalMatrixFillRec(rowCount, columnCount, points, row - 1, column - 1, Direction.TOP_LEFT, value);
                }
                break;
        }

        return filled;
    }

    // Нерекурсивный вариант
    // Возвращаем количество заполненных точек
    private static int diagonalMatrixFillNonRec(int rowCount, int columnCount, int[][] points, int row, int column, Direction direction, int value) {
        boolean finished = false; // Флаг остановки цикла и выходи из функции в случае, если некуда больше идти
        int filled = 0; // счетчик заполненных точек

        while (!finished) {
            // Если значения на точке нет -- записываем его
            if (points[row][column] == 0) {
                points[row][column] = value++;
                filled += 1;
            }

            // Если пришли в угол -- выходим
            if ((row == 0 && column == 0) || (row == 0 && column == columnCount - 1) || (row == rowCount - 1 && column == 0) || (row == rowCount - 1 && column == columnCount - 1)) {
                break;
            }

            switch (direction) {
                // Идем вверх-вправо, вниз-вправо, вверх-влево (куда сможем раньше)
                case TOP_RIGHT:
                    if (row - 1 >= 0 && column + 1 < columnCount && points[row - 1][column + 1] == 0) {
                        row -= 1;
                        column += 1;
                        direction = Direction.TOP_RIGHT;
                    } else if (row + 1 < rowCount && column + 1 < columnCount && points[row + 1][column + 1] == 0) {
                        row += 1;
                        column += 1;
                        direction = Direction.BOTTOM_RIGHT;
                    } else if (row - 1 >= 0 && column - 1 >= 0 && points[row - 1][column - 1] == 0) {
                        row -= 1;
                        column -= 1;
                        direction = Direction.TOP_LEFT;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;

                // Идем вниз-вправо, вверх-вправо, вниз-влево (куда сможем раньше)
                case BOTTOM_RIGHT:
                    if (row + 1 < rowCount && column + 1 < columnCount && points[row + 1][column + 1] == 0) {
                        row += 1;
                        column += 1;
                        direction = Direction.BOTTOM_RIGHT;
                    } else if (row - 1 >= 0 && column + 1 < columnCount && points[row - 1][column + 1] == 0) {
                        row -= 1;
                        column += 1;
                        direction = Direction.TOP_RIGHT;
                    } else if (row + 1 < rowCount && column - 1 >= 0 && points[row + 1][column - 1] == 0) {
                        row += 1;
                        column -= 1;
                        direction = Direction.BOTTOM_LEFT;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;

                // Идем вверх-влево, вверх-вправо, вниз-влево (куда сможем раньше)
                case TOP_LEFT:
                    if (row - 1 >= 0 && column - 1 >= 0 && points[row - 1][column - 1] == 0) {
                        row -= 1;
                        column -= 1;
                        direction = Direction.TOP_LEFT;
                    } else if (row - 1 >= 0 && column + 1 < columnCount && points[row - 1][column + 1] == 0) {
                        row -= 1;
                        column += 1;
                        direction = Direction.TOP_RIGHT;
                    } else if (row + 1 < rowCount && column - 1 >= 0 && points[row + 1][column - 1] == 0) {
                        row += 1;
                        column -= 1;
                        direction = Direction.BOTTOM_LEFT;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;

                // Идем вниз-влево, вниз-вправо, вверх-влево (куда сможем раньше)
                case BOTTOM_LEFT:
                    if (row + 1 < rowCount && column - 1 >= 0 && points[row + 1][column - 1] == 0) {
                        row += 1;
                        column -= 1;
                        direction = Direction.BOTTOM_LEFT;
                    } else if (row + 1 < rowCount && column + 1 < columnCount && points[row + 1][column + 1] == 0) {
                        row += 1;
                        column += 1;
                        direction = Direction.BOTTOM_RIGHT;
                    } else if (row - 1 >= 0 && column - 1 >= 0 && points[row - 1][column - 1] == 0) {
                        row -= 1;
                        column -= 1;
                        direction = Direction.TOP_LEFT;
                    } else {
                        // Нужно останавливаться, т.к. некуда идти
                        finished = true;
                    }
                    break;
            }
        }

        return filled;
    }
}
