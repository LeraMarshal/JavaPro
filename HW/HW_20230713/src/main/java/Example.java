import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Example {
    /*
    Написать класс Car, у которого будут поля:
     - цвет
     - boolean - спортивная или нет
     - Enum марка
     - int максимальная скорость

    Написать класс Developer, у которого будут:
     - имя
     - фамилия
     - возраст
     - зарплата
     - char пол
     - машина

    Написать класс Generator, который генерирует Developers и Cars.
    У генератора будут два метода, которые будут генерировать машины и девелоперов в зависимости от количества,
     которые вы передадите в параметр.

    (В помощь библиотека Faker для генерации любых имен, фамилий и тд.)

    Написать класс Обработчик, который будет:
    - проходиться по листу Developers и выводить всех мужчин, у которых спортивная машина
    - группировать по полу все машины (у мужчин одни, у женщин другие)
    - Переводить в Map ключ-Developer, а значение - максимальная скорость авто
    - записывать в текстовый файл всех девелоперов в формате : Jonn-[auto: BMW ::: salary: 12345] в столбик. Т.е в файле нужна только эта инфа
     */

    public static void main(String[] args) {
        Generator generator = new Generator(new Faker(), new Random());
        Handler handler = new Handler();

        List<Developer> developers = generator.createNewRandomDeveloper(10);
        System.out.println(developers);

        handler.printAllMenWithSportCar(developers);
        handler.printCarsByGender(developers);

        System.out.println(handler.getDeveloperWithMaxSpeedOfCar(developers));

        try {
            handler.writeDevelopersToFile(developers);
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }
}
