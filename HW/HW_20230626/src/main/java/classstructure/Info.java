package classstructure;

import java.util.*;

public class Info {
    public void checkPosition(Employee employee) {
        System.out.println(employee.getName() + " " + employee.getPosition().toString());
    }

    public double avgSalaryDep(Set<Employee> employees, Department department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElseThrow();

//        if (employees.isEmpty()) {
//            throw new IllegalArgumentException("No employees found");
//        }
//
//        double sum = 0;
//        int count = 0;
//        for (Employee employee : employees) {
//            if (employee.getDepartment() == department) {
//                sum += employee.getSalary();
//                count += 1;
//            }
//        }
//        return sum / count;
    }

    public double maxSalary(Set<Employee> employeeSet, Department department) {
        return employeeSet.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow();

//        if (employeeSet.isEmpty()){
//            throw new IllegalArgumentException("No employees found");
//        }
//
//        double maxSalary = Integer.MIN_VALUE;
//
//        for (Employee employee : employeeSet) {
//            if (employee.getDepartment() == department) {
//                if (employee.getSalary() > maxSalary) {
//                    maxSalary = employee.getSalary();
//                }
//            }
//        }
//
//        return maxSalary;
    }

    public double minSalary(Set<Employee> employeeSet, Department department) {
        return employeeSet.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow();

//        double minSalary = Integer.MAX_VALUE;
//
//        for (Employee employee : employeeSet) {
//            if (employee.getDepartment() == department) {
//                if (employee.getSalary() < minSalary) {
//                    minSalary = employee.getSalary();
//                }
//            }
//        }
//
//        System.out.println(minSalary);
    }

    public void getPromotion(Employee employee) {
        Objects.requireNonNull(employee);

        employee.setSalary(employee.getSalary() * 1.25);
        employee.setPosition(employee.getPosition().next());

//        switch (employee.getPosition()) {
//            case JUNIOR -> employee.setPosition(Position.MIDDLE);
//            case MIDDLE -> employee.setPosition(Position.SENIOR);
//        }
    }

    // by: Evgeniy Shulimenko, modified
    public void getPromotion2(Employee employee) {
        Objects.requireNonNull(employee);

        int indexOfPosition = -1;
        Position currentPosition = employee.getPosition();

        Position[] positions = Position.values();
        for (int i = 0; i < positions.length; i++) {
            if (currentPosition == positions[i]) {
                indexOfPosition = i;
            }
        }

        if (indexOfPosition >= 0 && indexOfPosition < positions.length - 1) {
            employee.setPosition(positions[indexOfPosition + 1]);
        }

        employee.setSalary(employee.getSalary() * 1.25);
    }
}
