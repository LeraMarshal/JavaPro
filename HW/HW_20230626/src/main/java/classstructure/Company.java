package classstructure;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Company {
    private String name;
    private Set<Employee> employees = new HashSet<>();
    private Set<Department> departments = new HashSet<>();

    public Company(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        Objects.requireNonNull(employee);

        employees.add(employee);
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", employeeSet=" + employees +
                ", departmentSet=" + departments +
                '}';
    }
}
