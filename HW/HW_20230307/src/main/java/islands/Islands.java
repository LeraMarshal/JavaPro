package islands;

import java.util.Arrays;

public class Islands {
    private static final byte SIZE_NOT_AN_ISLAND = -1; // Размер, который вернем, если находимся не на острове

    public static void main(String[] args) {
        // points:
        // 0 -> {1, 1, 0, 0, 0, 1}
        // ...
        // 5 -> {0, 0, 0, 1, 0, 0}

//        byte[][] points = new byte[][]{
//                {1, 1, 0, 0, 0, 1},
//                {0, 0, 0, 0, 1, 1},
//                {0, 1, 1, 1, 0, 0},
//                {0, 1, 0, 0, 1, 0},
//                {0, 1, 1, 1, 1, 0},
//                {0, 0, 0, 0, 0, 0},
//        };
        byte[][] points = new byte[][]{
                {1, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 1, 1},
                {0, 0, 1, 1, 0, 1},
                {0, 1, 0, 0, 1, 1},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
        };

        System.out.println(Arrays.toString(getMaxIslandSizeAndCount(points)));
    }

    // Возвращаем массив из двух элементов
    // Элемент 0 -- максимальный размер острова
    // Элемент 1 -- количество островов максимального размера
    public static int[] getMaxIslandSizeAndCount(byte[][] points) {
        int rowCount = points.length;
        int columnCount = points[0].length;

        // Будем отмечать, какие точки на карте уже посетили
        boolean[][] visited = new boolean[rowCount][columnCount];

        int maxIslandSize = 0;
        int maxIslandSizeCount = 0;

        for (int row = 1; row < rowCount - 1; row++) {
            for (int column = 1; column < columnCount - 1; column++) {
                // Если не нужно идти в эту точку, пропускаем ее
                if (!shouldVisitPoint(points, visited, row, column)) {
                    continue;
                }

                // Вычисляем размер острова, который находится на точке points[row][column]
                int islandSize = getIslandSize(points, visited, row, column);
                System.out.printf("points[%d][%d] island size = %d\n", row, column, islandSize);

                if (islandSize > maxIslandSize) {
                    // Меняем максимальный размер и сбрасываем количество в 1
                    maxIslandSize = islandSize;
                    maxIslandSizeCount = 1;
                } else if (islandSize > 0 && islandSize == maxIslandSize) {
                    // Увеличиваем количество на 1
                    maxIslandSizeCount += 1;
                }
            }
        }

        return new int[]{maxIslandSize, maxIslandSizeCount};
    }

    // Возвращает размер острова,
    private static int getIslandSize(byte[][] points, boolean[][] visited, int row, int column) {
        // Отмечаем точку как посещенную
        visited[row][column] = true;

        // Размер острова, начиная с текущей точки
        int size = 1;
        // Если мы дошли до края, отмечаем, что мы не на острове
        boolean notAnIsland = (row == 0 || row == points.length - 1 || column == 0 || column == points[row].length - 1);

        // Идем вверх, если нужно
        if (shouldVisitPoint(points, visited, row - 1, column)) {
            int partSize = getIslandSize(points, visited, row - 1, column);
            // Если нам вернулся отрицательный размер верхней части -- ставим флаг, что мы не на острове
            notAnIsland |= partSize < 0;
            // Увеличиваем размер острова на длину верхней части
            size += partSize;
        }

        // Идем вправо, если нужно
        if (shouldVisitPoint(points, visited, row, column + 1)) {
            int partSize = getIslandSize(points, visited, row, column + 1);
            notAnIsland |= partSize < 0;
            size += partSize;
        }

        // Идем вниз, если нужно
        if (shouldVisitPoint(points, visited, row + 1, column)) {
            int partSize = getIslandSize(points, visited, row + 1, column);
            notAnIsland |= partSize < 0;
            size += partSize;
        }

        // Идем влево, если нужно
        if (shouldVisitPoint(points, visited, row, column - 1)) {
            int partSize = getIslandSize(points, visited, row, column - 1);
            notAnIsland |= partSize < 0;
            size += partSize;
        }

        return notAnIsland ? SIZE_NOT_AN_ISLAND : size;
    }

    // Возвращает false, если вышли за пределы карты, или уже посещали данную точку, или на точке значение 0
    // В противном случае, возвращает true
    private static boolean shouldVisitPoint(byte[][] points, boolean[][] visited, int row, int column) {
        // Если вышли за пределы карты по строкам, возвращаем false
        if (row >= points.length || row < 0) {
            return false;
        }

        // Если вышли за пределы карты по столбцам, возвращаем false
        if (column >= points[row].length || column < 0) {
            return false;
        }

        // Если уже посещали точку, возвращаем false
        if (visited[row][column]) {
            return false;
        }

        // Возвращаем true, если на точке стоит не ноль, в противном случае -- возвращаем false
        return points[row][column] > 0;
    }
}
