package celem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class CommonElemRemoverTest {
    @Test
    public void removeCommonElemException() {
        assertThrows(NullPointerException.class, () -> CommonElemRemover.removeCommonElem(null));
    }

    @ParameterizedTest
    @MethodSource("removeCommonElemArgs")
    public void removeCommonElem(int[] expect, int[] arr) {
        assertArrayEquals(expect, CommonElemRemover.removeCommonElem(arr));
    }

    private static Stream<Arguments> removeCommonElemArgs() {
        return Stream.of(
                arguments(
                        new int[]{0, 3, -2, 4, 2},
                        new int[]{0, 3, -2, 4, 3, 2}
                ),
                arguments(
                        new int[]{1, 2, 3, 4},
                        new int[]{1, 2, 3, 4}
                ),
                arguments(
                        new int[0],
                        new int[0]
                )
        );
    }

//    @Test
//    void removeCommonElem() {
//        assertArrayEquals(new int[]{0, 3, -2, 4, 2}, CommonElemRemover.removeCommonElem(new int[]{0, 3, -2, 4, 3, 2}));
//        assertArrayEquals(new int[]{1, 2, 3, 4}, CommonElemRemover.removeCommonElem(new int[]{1, 2, 3, 4}));
//    }
}