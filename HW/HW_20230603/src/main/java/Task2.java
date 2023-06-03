import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        findAllPermutations("abc");

        System.out.println(isAnagram("abc", "cba"));

        printAllSubstrings("abc");

        findAllCombinations("abc");
        
    }

    // Напишите программу, которая принимает на вход строку и выводит все перестановки символов этой строки
    private static void findAllPermutations(String str) {
        Queue<String> queue = new LinkedList<>();
        queue.add("" + str.charAt(0));

        for (int i = 1; i < str.length(); i++) {
            int size = queue.size();
            while (size-- > 0) {
                String tmp = queue.remove();
                for (int k = 0; k <= tmp.length(); k++) {
                    queue.add(tmp.substring(0, k) + str.charAt(i) + tmp.substring(k));
                }
            }
        }

        System.out.println(queue);
    }

    // Напишите программу, которая принимает на вход две строки и проверяет, являются ли они анаграммами (т.е. содержат ли они одинаковые символы в разном порядке)
    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            map.merge(str1.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < str2.length(); i++) {
            map.merge(str2.charAt(i), -1, Integer::sum);
        }

        return map.values().stream().allMatch(value -> value == 0);
    }

    // Напишите программу, которая принимает на вход строку и выводит все подстроки этой строки
    public static void printAllSubstrings(String str) {
        for (int l = 0; l < str.length(); l++) {
            for (int r = l + 1; r <= str.length(); r++) {
                System.out.println(str.substring(l, r));
            }
        }
    }

    // Напишите программу, которая принимает на вход строку и выводит все возможные комбинации символов этой строки в порядке возрастания их длины
    private static void findAllCombinations(String str) {
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            queue.add("" + str.charAt(i));
        }

        for (int i = 0; i < str.length(); i++) {
            int size = queue.size();
            while (size-- > 0) {
                String tmp = queue.remove();
                System.out.println(tmp);
                for (int k = 0; k <= tmp.length(); k++) {
                    queue.add(tmp.substring(0, k) + str.charAt(i) + tmp.substring(k));
                }
            }
        }
        while (!queue.isEmpty()){
            System.out.println(queue.remove());
        }
    }

    // Реализуйте параметризированный метод "swap", который принимает массив элементов и два индекса внутри массива
    // Метод должен поменять местами элементы по указанным индексам
    public static <T> void swap(T[] arr, int i, int j){
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Напишите параметризированный метод "printArray", который принимает массив элементов и выводит их на консоль
    // Метод должен работать с любыми типами данных
    public static <T> void printArr(T[] arr){
        Arrays.stream(arr).forEach(System.out::println);
    }
}
