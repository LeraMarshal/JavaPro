import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing {
    public static void main(String[] args) {
        try {
            System.out.println(countDomains("./HW/HW_20230807/src/main/resources/emails.txt"));
            System.out.println();
            System.out.println(replaceScheme(new File("./HW/HW_20230807/src/main/resources/urls.txt")));
            System.out.println();
            System.out.println(findProductsAbove(new FileInputStream("./HW/HW_20230807/src/main/resources/products.txt"), 10));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    Вам дан текстовый файл, содержащий список email-адресов.
    Извлечь все уникальные домены электронной почты и подсчитать,
    сколько раз каждый домен встречается в списке.

    aaa
    john.doe@example.com
    john.doe@example.com@abc.ru
    jane.smith@example.org
    james.bond@example.com
    jane.smith@example.org
    jane.smith@example.net
    */
    public static Map<String, Integer> countDomains(String path) throws IOException {
        Map<String, Integer> counts = new HashMap<>();
        Pattern pattern = Pattern.compile("^.+@(.+?\\..+?)$");

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (!matcher.matches()) {
                    continue;
                }

                String domain = matcher.group(1);
                counts.merge(domain, 1, Integer::sum);
            }
        }

        return counts;
    }

    /*
    Вам дан текстовый файл, содержащий список URL-адресов.
    Найти все URL, которые содержат протокол "https://" и заменить их на "http://"
    https://www.example.com
    http://www.example.org
    https://www.example.net
    https://www.example.com/about
    */
    public static List<String> replaceScheme(File file) throws IOException {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("^https://");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                list.add(matcher.replaceFirst("http://"));
            }
        }
        return list;
    }

    /*
    Вам дан текстовый файл, содержащий список кодов продуктов в формате: <product_code>:<quantity>.
    Найти все продукты с количеством больше 10 и вывести их коды и количество.
    ABC123:5
    XYZ789:12
    DEF456:8
    JKL012:15
    MNO345:6
    */
    public static Map<String, Integer> findProductsAbove(InputStream inputStream, int threshold) throws IOException {
        Map<String, Integer> products = new HashMap<>();
        Pattern pattern = Pattern.compile("^(.+):(\\d+)$");

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (!matcher.matches()) {
                    continue;
                }

                String code = matcher.group(1);
                int quantity = Integer.parseInt(matcher.group(2));

                if (quantity > threshold) {
                    products.put(code, quantity);
                }
            }
        }

        return products;
    }
}
