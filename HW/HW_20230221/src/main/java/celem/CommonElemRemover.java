package celem;

import java.util.Arrays;
import java.util.Objects;

public class CommonElemRemover {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeCommonElem(new int[0])));
        System.out.println(Arrays.toString(removeCommonElem(new int[]{0, 3, -2, 4, 3, 2})));
    }

    /*
    Напишите программу на Java и тест для нее для удаления
    повторяющихся элементов из массива.
    example input:
    [0,3,-2,4,3,2]

    example expected:
    [0,3,-2,4,2]
     */
    public static int[] removeCommonElem(int[] arr) {
        Objects.requireNonNull(arr);

        return Arrays.stream(arr).distinct().toArray();
    }
}
