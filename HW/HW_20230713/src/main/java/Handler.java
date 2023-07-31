import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Handler {
    public void printAllMenWithSportCar(List<Developer> developers) {
        developers.stream()
                .filter(dev -> dev.getCar().isSportCar())
                .forEach(System.out::println);
    }

    public void printCarsByGender(List<Developer> developers) {
        System.out.println(developers.stream()
                .collect(Collectors.groupingBy(
                        Developer::getGender,
                        Collectors.mapping(
                                Developer::getCar,
                                Collectors.toSet()
                        )
                )));
    }

    public Map<Developer, Integer> getDeveloperWithMaxSpeedOfCar(List<Developer> developers) {
        return developers.stream()
                .collect(Collectors.toMap(dev -> dev, dev -> dev.getCar().getMaxSpeed()));
    }

    public void writeDevelopersToFile(List<Developer> developers) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("HW_20230713_developers.txt"))) {
            for (Developer developer : developers) {
                // Jonn-[auto: BMW ::: salary: 12345]
                writer.write(String.format(
                        "%s-[auto: %s ::: salary: %s]\n",
                        developer.getName(),
                        developer.getCar().getModelOfCar(),
                        developer.getSalary()
                ));
            }
        }
    }
}
