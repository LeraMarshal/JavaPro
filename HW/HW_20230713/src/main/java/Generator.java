import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Generator {
    private Faker faker;
    private Random random;

    public Generator(Faker faker, Random random) {
        this.faker = faker;
        this.random = random;
    }

    public List<Developer> createNewRandomDeveloper(int count) {
        return createNewRandomCar(count).stream().map(car -> {
            String name = faker.name().firstName();
            String lastName = faker.name().lastName();
            int age = random.nextInt(18, 70);
            long salary = random.nextLong(3000L, 10000L);
            char gender = faker.demographic().sex().charAt(0);

            return new Developer(name, lastName, age, salary, gender, car);
        }).collect(Collectors.toList());
    }

    public List<Car> createNewRandomCar(int count) {
        return IntStream.range(0, count).mapToObj(i -> {
            String color = faker.color().name();
            boolean isSportCar = random.nextBoolean();
            ModelOfCar modelOfCar = ModelOfCar.values()[random.nextInt(0, ModelOfCar.values().length)];
            int maxSpeed = random.nextInt(120, 250);

            return new Car(color, isSportCar, modelOfCar, maxSpeed);
        }).collect(Collectors.toList());
    }
}
