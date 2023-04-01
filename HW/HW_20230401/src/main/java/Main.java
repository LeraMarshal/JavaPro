import java.lang.reflect.Field;

public class Main {
    /*
    Сделать по аналогии с примером с аннотацией  @MyName (см. код в репозитории):

    Имеется класс Student с полями name, surname, age. Создать аннотацию @StudentInfo и соответствующий инжектор через рефлексию,
    чтобы с их помощью иметь возможность инициализировать переменные типа Student. Например:


    public class School {
        @StudentInfo(name = "Tom", surname = "Smith", age = 15)
        private Student student1;

        @StudentInfo(name = "Mary", surname = "Jennings", age = 17)
        private Student student2;
    }
    */

    public static void main(String[] args) {
        School school = new School();

        try {
            inject(school);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(school);
    }

    private static void inject(School school) throws IllegalAccessException {
        for (Field declaredField : school.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(StudentInfo.class)) {
                StudentInfo studentInfo = declaredField.getAnnotation(StudentInfo.class);

                declaredField.setAccessible(true);
                declaredField.set(school, new Student(studentInfo.name(), studentInfo.surname(), studentInfo.age()));
            }
        }
    }
}
