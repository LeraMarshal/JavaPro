import java.util.*;

public class Task1 {
    /*
     * Создать новую Map<Character, String>,
     * где ключом будет символ из Character[][],
     * а значением будет соответствующая строка из String[][].
     * Необходимо выполнить следующие шаги:
     */
    public static void main(String[] args) {
        Map<Boolean[][], Character[][]> map1 = new HashMap<>(Map.of(
                new Boolean[][]{
                        {true, false},
                        {false, true}
                },
                new Character[][]{
                        {null, null},
                        {null, null}
                }
        ));

        Map<Integer[][], String[][]> map2 = new HashMap<>(Map.of(
                new Integer[][]{
                        {1, 2},
                        {3, 4}
                },
                new String[][]{
                        {null, null},
                        {null, null}
                }
        ));

        System.out.println(transformMaps(map1, map2));
    }

    public static Map<Character, String> transformMaps(Map<Boolean[][], Character[][]> map1, Map<Integer[][], String[][]> map2) {
        Map<Character, String> result = new HashMap<>();
        List<Character> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        map1.forEach((k, v) -> {
            for (int i = 0; i < k.length; i++) {
                for (int j = 0; j < k[i].length; j++) {
                    v[i][j] = (k[i][j] ? 'T' : 'F');
                    keys.add(v[i][j]);
                }
            }
        });

        map2.forEach((k, v) -> {
            for (int i = 0; i < k.length; i++) {
                for (int j = 0; j < k[i].length; j++) {
                    v[i][j] = k[i][j].toString();
                    values.add(v[i][j]);
                }
            }
        });

        if (keys.size() != values.size()) throw new IllegalStateException("The number of keys and values must match");

        for (int i = 0; i < keys.size(); i++) {
            result.merge(keys.get(i), values.get(i), (s1, s2) -> s1 + " " + s2);
        }

        return result;

    }

}
