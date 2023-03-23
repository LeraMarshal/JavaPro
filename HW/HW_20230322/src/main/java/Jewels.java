import java.util.HashMap;
import java.util.Map;

public class Jewels {
    /*
     * Есть две стринги
     * String jewelery = "AuiP"; --> виды драгоценностей
     * String stones = "AUUUUiHNhfgkpPKjAAaakndsdl"; --> набор камней который
     * содержит простые камни и драгоценности
     * !!! Найти сколько и каких драгоценностей в наборе камней
     */
    public static void main(String[] args) {
        String jewelery = "AuiP";
        String stones = "AUUUUiHNhfgkpPKjAAaakndsdl";

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < stones.length(); i++) {
            map.merge(stones.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < jewelery.length(); i++) {
            Character jewel = jewelery.charAt(i);
            System.out.printf("Jewel [%c] -> [%d]\n", jewel, map.get(jewel));
        }
    }
}
