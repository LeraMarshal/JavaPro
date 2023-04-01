public class School {
    @StudentInfo(name = "Tom", surname = "Smith", age = 15)
    private Student student1;

    @StudentInfo(name = "Mary", surname = "Jennings", age = 17)
    private Student student2;

    @StudentInfo(name = "John", surname = "Smith", age = 20)
    private Student student3;

    @Override
    public String toString() {
        return "School{" +
                "student1=" + student1 +
                ", student2=" + student2 +
                ", student3=" + student3 +
                '}';
    }
}