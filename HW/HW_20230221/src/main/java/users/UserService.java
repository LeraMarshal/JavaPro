package users;

import java.util.List;
import java.util.Objects;

public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        Objects.requireNonNull(repository);

        this.repository = repository;
    }

    public boolean isActive(int id) {
        // Обращение к БД, доставать пользователя по id и проверять поле isActive
        User user = repository.getUserById(id);

        return user.isActive();
    }

    public void deactivate(int id) {
        User user = repository.getUserById(id);
        user.setActive(false);

        repository.saveUser(user);
    }

    /*
    В классе UserService (см. код занятия в репозитории) реализовать метод public void deleteInactive(List users),
    который будет удалять с помощью метода UserRepository deleteUser(int id) из БД всех неактивных пользователей из списка.
    Составить тест на данный метод с применением Mockito.
     */
    public void deleteInactive(List<User> userList){
        Objects.requireNonNull(userList);

        for (User user : userList) {
            if (!user.isActive()){
                repository.deleteUser(user.getId());
            }
        }

//        userList.stream()
//                .filter(user -> !user.isActive())
//                .forEach(user -> repository.deleteUser(user.getId()));
    }
}
