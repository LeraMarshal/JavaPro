package users;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User getUserById(int id) {
        // логика соединения с БД (connect to DB and get date)
        return new User(100, "Test user", true);
    }

    @Override
    public void saveUser(User user) {
        // обращение к БД и запись инфо о пользователе как отдельную запись (connect to DB and save date)
    }

    @Override
    public void deleteUser(int id) {
        // connect to DB, search and delete inactive users by id (from the List)
    }
}
