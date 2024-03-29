package de.marshal.hw20230813;

public class Example {
    /*
    Классы:

    User:
    int id
    String username
    String email
    List<Role> roles
    Методы:
    getUserInfo(): Возвращает строку с информацией о пользователе.
    addRole(Role role): Добавляет роль пользователю.
    removeRole(Role role): Удаляет роль у пользователя.
    Бизнес-логика: Класс описывает пользователя с уникальным идентификатором, именем, адресом электронной почты и списком ролей. Пользователь может иметь несколько ролей.

    Role:
    int id
    String roleName
    String description
    Методы:
    getRoleInfo(): Возвращает строку с информацией о роли.
    setDescription(String description): Устанавливает описание роли.
    Бизнес-логика: Класс описывает роль с уникальным идентификатором, названием и описанием.

    AuthenticationService:
    UserDatabase userDatabase
    Методы:
    authenticate(String username, String password): Проверяет аутентификацию пользователя.
    grantAccess(User user): Предоставляет доступ пользователю.
    revokeAccess(User user): Отзывает доступ у пользователя.
    Бизнес-логика: Класс предоставляет методы для аутентификации пользователей, предоставления и отзыва доступа.

    UserDatabase:
    List<User> users
    Методы:
    getUserById(int id): Возвращает пользователя по его идентификатору.
    getUserByUsername(String username): Возвращает пользователя по имени пользователя.
    addUser(User user): Добавляет нового пользователя в базу данных.
    Бизнес-логика: Класс управляет хранением и доступом к пользователям.
     */
}
