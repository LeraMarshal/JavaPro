import java.util.*;
import java.util.stream.Collectors;

public class Task1 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,3,4,4,5,5};
        System.out.println(arrToListDistinct(arr));

        System.out.println(arrToListReverse(arr));

        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {6,7,1,2,3};
        System.out.println(findCommonElems(arr1, arr2));

        System.out.println(findUniqElemCnt(arr));
    }

    // Удаление дубликатов: Напишите метод, который принимает массив int и возвращает ArrayList,
    // содержащий все уникальные элементы исходного массива, сохраняя их порядок
    public static List<Integer> arrToListDistinct(int[] arr){
        return Arrays.stream(arr).boxed().distinct().collect(Collectors.toList());
    }

    // Обратный порядок: Напишите метод, который принимает массив int и возвращает ArrayList,
    // содержащий элементы исходного массива в обратном порядке
    public static List<Integer> arrToListReverse(int[] arr){
        List<Integer> res = new ArrayList<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            res.add(arr[i]);
        }

        return res;
    }

    // Задачи на Set:
    // Поиск пересечения: Напишите метод, который принимает два массива int и возвращает Set,
    // содержащий элементы, которые присутствуют и в первом, и во втором массивах
    public static Set<Integer> findCommonElems(int[] arr1, int[] arr2){
//        Set<Integer> set1 = Arrays.stream(arr1).boxed().collect(Collectors.toSet());
//        Set<Integer> set2 = Arrays.stream(arr2).boxed().collect(Collectors.toSet());
//
//        set1.retainAll(set2);

//        return set1;

        Map<Integer, Integer> map = new HashMap<>();

        for (int j : arr1) {
            map.merge(j, 1, Integer::sum);
        }

        for (int j : arr2) {
            map.merge(j, -1, Integer::sum);
        }

        return map.entrySet().stream()
                .filter(pair -> pair.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    // Подсчет уникальных элементов: Напишите метод, который принимает массив int
    // и возвращает количество уникальных элементов в этом массиве, используя Set для хранения уникальных значений
    public static long findUniqElemCnt(int[] arr){
//        return Arrays.stream(arr).boxed().collect(Collectors.toSet()).size();

        return Arrays.stream(arr).boxed().distinct().count();
    }
}
