public class Developer {
    private String name;
    private String lastName;
    private int age;
    private long salary;
    private char gender;
    private Car car;

    public Developer(String name, String surname, int age, long salary, char gender, Car car) {
        this.name = name;
        this.lastName = surname;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", surname='" + lastName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender=" + gender +
                ", car=" + car +
                '}';
    }
}
