import java.util.Arrays;

// 1. https://ru.wikipedia.org/wiki/Поиск_в_глубину (использован)
// 2. Алгоритм Дэйкстра (не реализован)
public class Task7 {
    // Напишите программу, которая находит наименьший путь от верхнего левого угла
    // до нижнего правого угла в двумерном массиве. Каждая ячейка содержит стоимость прохода.

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int[][] costs = calcMinCosts(grid, 0, 0);

        for (int[] cost : costs) {
            System.out.println(Arrays.toString(cost));
        }

        printMinPath(costs, grid.length - 1, grid[0].length - 1);
    }

    public static int[][] calcMinCosts(int[][] grid, int startRow, int startColumn) {
        int[][] costs = new int[grid.length][grid[0].length];
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < costs.length; i++) {
            Arrays.fill(costs[i], -1);
        }
        costs[startRow][startColumn] = 0;

        calcMinCosts(grid, visited, costs, startRow, startColumn);

        return costs;
    }

    public static void calcMinCosts(int[][] grid, boolean[][] visited, int[][] costs, int row, int column) {
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[row].length || visited[row][column]) {
            return;
        }

        visited[row][column] = true;

        // Если вверху есть точка И ее стоимость не определена ИЛИ ее стоимость больше стоимости прохода через текущую точку
        if (row - 1 >= 0 && (costs[row - 1][column] == -1 || costs[row][column] + grid[row - 1][column] < costs[row - 1][column])) {
            // стоимость в текущей точке + стоимость пути вверх
            costs[row - 1][column] = costs[row][column] + grid[row - 1][column];
            //  т.к. нужно пересчитать теперь стоимости соседей данной точки
            visited[row - 1][column] = false;
        }

        if (row + 1 < grid.length && (costs[row + 1][column] == -1 || costs[row][column] + grid[row + 1][column] < costs[row + 1][column])) {
            costs[row + 1][column] = costs[row][column] + grid[row + 1][column];
            visited[row + 1][column] = false;
        }

        if (column - 1 >= 0 && (costs[row][column - 1] == -1 || costs[row][column] + grid[row][column - 1] < costs[row][column - 1])) {
            costs[row][column - 1] = costs[row][column] + grid[row][column - 1];
            visited[row][column - 1] = false;
        }

        if (column + 1 < grid[row].length && (costs[row][column + 1] == -1 || costs[row][column] + grid[row][column + 1] < costs[row][column + 1])) {
            costs[row][column + 1] = costs[row][column] + grid[row][column + 1];
            visited[row][column + 1] = false;
        }

        calcMinCosts(grid, visited, costs, row - 1, column);
        calcMinCosts(grid, visited, costs, row + 1, column);
        calcMinCosts(grid, visited, costs, row, column - 1);
        calcMinCosts(grid, visited, costs, row, column + 1);
    }

    private static void printMinPath(int[][] costs, int row, int column) {
        int topCost = (row - 1 >= 0) ? costs[row - 1][column] : Integer.MAX_VALUE;
        int downCost = (row + 1 < costs.length) ? costs[row + 1][column] : Integer.MAX_VALUE;
        int leftCost = (column - 1 >= 0) ? costs[row][column - 1] : Integer.MAX_VALUE;
        int rightCost = (column + 1 < costs[row].length) ? costs[row][column + 1] : Integer.MAX_VALUE;

        int currentCost = costs[row][column];

        int minCost = topCost;
        if (downCost < minCost) {
            minCost = downCost;
        }
        if (leftCost < minCost) {
            minCost = leftCost;
        }
        if (rightCost < minCost) {
            minCost = rightCost;
        }

        // если все стоимости вокруг (соседи) >= текущей -> достигли начальной точки
        if (minCost >= currentCost) {
            System.out.printf("%d,%d\n",row,column);
            return;
        }

        System.out.printf("%d,%d <- ", row, column);

        if (topCost == minCost) {
            printMinPath(costs, row - 1, column);
        } else if (downCost == minCost) {
            printMinPath(costs, row + 1, column);
        } else if (leftCost == minCost) {
            printMinPath(costs, row, column - 1);
        } else {
            printMinPath(costs, row, column + 1);
        }
    }
}
