package celem;

import java.util.*;

public class CommonElemFinder {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 5, 5, 8, 9, 7, 10};
//        int[] arr1 = {2, 2, 5, 5, 8, 9, 2, 10};
//        int[] arr2 = {1, 0, 6, 15, 6, 4, 7, 0};
        int[] arr2 = {7, 0, 6, 1, 6, 4, 1, 0};

        System.out.println(Arrays.toString(findCommonElem(arr1, arr2)));

    }

    /*
    1. Напишите программу на Java и тест для нее для поиска общих элементов между двумя массивами целых чисел
    example input:

    Array1: [1, 2, 5, 5, 8, 9, 7, 10]
    Array2: [1, 0, 6, 15, 6, 4, 7, 0]

    example expected:
    [1,7]
     */
    public static int[] findCommonElem(int[] arr1, int[] arr2) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);

        if (arr1.length == 0 || arr2.length == 0) {
            return new int[0];
        }

        if (arr1 == arr2) {
            return arr1;
        }

        Set<Integer> set = new LinkedHashSet<>();
        for (int value : arr1) {
            set.add(value);
        }

        Set<Integer> result = new LinkedHashSet<>();
        for (int value : arr2) {
            if (set.contains(value)) {
                result.add(value);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();


//        ============

//        Set<Integer> set1 = new HashSet<>();
//        for (int value : arr1) {
//            set1.add(value);
//        }
//
//        Set<Integer> set2 = new HashSet<>();
//        for (int value : arr2) {
//            set2.add(value);
//        }
//
//        set1.retainAll(set2);
//
//        return set1.stream().mapToInt(Integer::intValue).toArray();

//        ============

//        int[] tmp = new int[Math.max(arr1.length, arr2.length)];
//        int pos = 0;
//
//        for (int i = 0; i < arr1.length; i++) {
//            for (int j = 0; j < arr2.length; j++) {
//                if (arr1[i] == arr2[j]) {
//                    boolean found = false;
//                    for (int k = 0; k < pos; ++k) {
//                        if (tmp[k] == arr1[i]) {
//                            found = true;
//                            break;
//                        }
//                    }
//
//                    if (!found) {
//                        tmp[pos++] = arr1[i];
//                    }
//                }
//            }
//        }
//
//        int[] result = new int[pos];
//        System.arraycopy(tmp, 0, result, 0, pos);
//
//        return result;
    }
}
