package users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static UserService service;
    private static UserRepository repository;
    private static User user1;
    private static User user2;

    @BeforeEach // инициализирует по порядку с тестами
    public void init() {
        // создаем мок - объект (чтобы не создавать соединение к БД напрямую)
        repository = Mockito.mock(UserRepository.class);
        service = new UserService(repository);

        user1 = new User(1, "Test user 1", true);
        user2 = new User(2, "Test user 2", false);

        // повторное обращение будет возвращать юзер 3 и тд (но с БД не использовать)
        Mockito.when(repository.getUserById(1))
                .thenReturn(user1)
                .thenReturn(user2);

        Mockito.when(repository.getUserById(2))
                .thenReturn(user2);

        // какой бы id не указали, будет возвращать user 2
//        Mockito.when(repository.getUserById(Mockito.anyInt()))
//                .thenReturn(user2);
    }

    @Test
    public void constructorException() {
        assertThrows(NullPointerException.class, () -> new UserService(null));
    }

    @Test
    void isActive() {
        assertTrue(service.isActive(1));  // true
        assertFalse(service.isActive(2)); // false
//        assertFalse(service.isActive(3)); // ошибка, но можно раскомментировать вызовы с anyInt (35-36)
    }

    @Test
    void deactivate() {
        assertTrue(user1.isActive());
        service.deactivate(1);
        assertFalse(user1.isActive());

        // проверка, что метод вызван 1 раз
        Mockito.verify(repository, Mockito.times(1)).saveUser(user1);

        service.deactivate(2);
        assertFalse(user2.isActive());

        Mockito.verify(repository, Mockito.times(1)).saveUser(user2);

        // проверяем, что всего вызовов saveUser было 2 (не важно, с какими параметрами)
        Mockito.verify(repository, Mockito.times(2)).saveUser(Mockito.any());
    }

    @Test
    void deleteInactive() {
        service.deleteInactive(List.of(user1, user2));
        Mockito.verify(repository).deleteUser(2);

        assertThrows(NullPointerException.class, () -> service.deleteInactive(null));
    }
}