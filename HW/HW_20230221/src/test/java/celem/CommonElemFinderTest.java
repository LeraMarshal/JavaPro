package celem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CommonElemFinderTest {
    @Test
    public void findCommonElemException() {
        assertThrows(NullPointerException.class, () -> CommonElemFinder.findCommonElem(new int[0], null));

        assertThrows(NullPointerException.class, () -> CommonElemFinder.findCommonElem(null, new int[0]));

        assertThrows(NullPointerException.class, () -> CommonElemFinder.findCommonElem(null, null));
    }

    @ParameterizedTest
    @MethodSource("findCommonElemArgs")
    public void findCommonElem(int[] expect, int[] arr1, int[] arr2) {
        assertArrayEquals(expect, sortedFindCommonElem(arr1, arr2));
    }

    private static Stream<Arguments> findCommonElemArgs() {
        return Stream.of(
                arguments(
                        new int[]{1, 7},
                        new int[]{1, 2, 5, 5, 8, 9, 7, 10},
                        new int[]{7, 0, 6, 1, 6, 4, 1, 0}
                ),
                arguments(
                        new int[0],
                        new int[]{1, 2, 5, 5, 8, 9, 7, 10},
                        new int[0]
                ),
                arguments(
                        new int[0],
                        new int[0],
                        new int[]{1, 2, 5, 5, 8, 9, 7, 10}
                ),
                arguments(
                        new int[0],
                        new int[0],
                        new int[0]
                ),
                arguments(
                        new int[0],
                        new int[]{1, 2, 3},
                        new int[]{4, 5, 6}
                )
        );
    }

//    @Test
//    public void findCommonElem() {
//        assertArrayEquals(
//                new int[]{1, 7},
//                sortedFindCommonElem(new int[]{1, 2, 5, 5, 8, 9, 7, 10}, new int[]{7, 0, 6, 1, 6, 4, 1, 0})
//        );
//
//        assertArrayEquals(
//                new int[0],
//                sortedFindCommonElem(new int[]{1, 2, 5, 5, 8, 9, 7, 10}, new int[0])
//        );
//
//        assertArrayEquals(
//                new int[0],
//                sortedFindCommonElem(new int[0], new int[]{1, 2, 5, 5, 8, 9, 7, 10})
//        );
//
//        assertArrayEquals(
//                new int[0],
//                sortedFindCommonElem(new int[0], new int[0])
//        );
//
//        assertArrayEquals(
//                new int[0],
//                sortedFindCommonElem(new int[]{1, 2, 3}, new int[]{4, 5, 6})
//        );
//    }

    private int[] sortedFindCommonElem(int[] arr1, int[] arr2) {
        int[] res = CommonElemFinder.findCommonElem(arr1, arr2);

        Arrays.sort(res);

        return res;
    }
}