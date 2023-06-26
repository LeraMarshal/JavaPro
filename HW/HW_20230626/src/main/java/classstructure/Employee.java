package classstructure;

public class Employee {
    private String name;
    private double salary;
    private Department department;
    private Position position;

    public Employee(String name, long salary, Department department, Position position) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                ", position=" + position +
                '}';
    }
}
