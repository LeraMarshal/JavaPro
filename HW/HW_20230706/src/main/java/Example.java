import java.util.Random;

public class Example {
    /*
    Создать класс Банковская карта:
    - Данные карты
    - Валюта
    - Статус

    (Все, что с большой буквы - это классы)

    Сделать класс ГенераторДанныхБанковскойКарты, который будет возвращать Данные Карты (String)
    Данные карты должны быть String вида:

    12345678123456780000999
    Первые 16 цифр это номер
    Потом дата (месяц/год)
    Потом CVV код

    Создать класс Клиент:
    - Имя
    - Карта
    - Баланс

    Создать класс Хранилище:
    - Map (храниться минимум 25 Клиентов. Ключ -> Клиент, Значение -> баланс)

    Написать в хранилище методы, которые на вход получают Map
     (+ может ещё какой параметр), а на выходе делают следующее:

    - Выводят всех клиентов, у которых определенный статус карты.
    - Группируют по балансу: у кого больше или меньше какой-то суммы.
    - Средний баланс по клиентам.
    - Выводят только номера карт всех клиентов в формате имя -> номер.
    - Выводят всех клиентов, у кого закончился срок действия карт.
    - Сортируют по балансу.
    - Группируют: у кого закончился срок карт, а у кого нет.
     */

    public static void main(String[] args) {
        Random random = new Random();
        BankCardDataGenerator generator = new BankCardDataGenerator(random);
        Store store = new Store();

        for (int i = 0; i < 25; i++) {
            Currency currency = Currency.values()[random.nextInt(Currency.values().length)];
            Status status = Status.values()[random.nextInt(Status.values().length)];

            BankCard bankCard = new BankCard(generator.generate(), currency, status);

            store.add(new Client("Client " + i, bankCard, random.nextLong(100000L)));
        }

        store.printClientsWithCardStatus(Status.CLOSED);
        System.out.println();

        store.printGroupingByBalance(50000);
        System.out.println();

        store.printAverageBalance();
        System.out.println();

        store.printClientsWithCardNumbers();
        System.out.println();

        store.printClientsWithExpiredCards();
        System.out.println();

        store.printClientsSortedByBalance();
        System.out.println();

        store.printGroupingByCardExpired();
        System.out.println();
    }
}
