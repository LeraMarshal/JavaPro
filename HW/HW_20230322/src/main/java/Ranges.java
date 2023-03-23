import java.util.Arrays;

public class Ranges {
    /*
     * String[] sa = {"2,5", "1-3,8", "4", "5-9", "7,9-2"};
     * String[] newA = {"25", "1238", "4", "56789", "798765432"};
     * Преобразовать массив |sa| в массив |newA|
     */
    public static void main(String[] args) {
        String[] arr = {"2,5", "1-3,8", "4", "5-9", "7,9-2", "7,9-2,3-5,10-10"};
        System.out.println(Arrays.toString(arr));

        String[] newArr = Arrays.stream(arr).map(Ranges::transform).toArray(String[]::new); // toArray(counts -> new String[counts])

        System.out.println(Arrays.toString(newArr));
    }

    public static String transform(String str) {
        StringBuilder sb = new StringBuilder();

        for (String s : str.split(",")) {
            String[] parts = s.split("-", 2);

            if (parts.length == 1) {
                sb.append(parts[0]);
            } else {
                int first = Integer.parseInt(parts[0]);
                int second = Integer.parseInt(parts[1]);
                int step = (first > second) ? -1 : 1;

                for (int i = first; i != second; i += step) {
                    sb.append(i);
                }

                sb.append(second);
            }
        }

        return sb.toString();
    }
}
