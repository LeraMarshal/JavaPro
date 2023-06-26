package classstructure;

public class Main {
    /*
    Создайте папку classStructure1
Создайте классы

- Company
    - name
    - Set<Employee>
    - Set<Department>

- Employee
    - name
    - Department(энам)
    - Position(энам)
    - double salary

- Info
    - Map<Position, List<String>>
    - checker() - на вход сотрудника, на выходе String в зависимости от позишн
    - avgSalaryDep() - на вход Set<Employee>, Department, на выход средняя зп по департаменту
    - maxSalary() - на вход Set<Employee>, Department, на выход max зп по департаменту + у кого такая зп(имя)
    - minSalary() - на вход Set<Employee>, Department, на выход min зп по департаменту + у кого такая зп(имя)
    - getPromotion() - на вход сотрудника. в методе - задать переход на одну позицию выше и зп на 25% больше.
     */
    public static void main(String[] args) {
        Employee e1 = new Employee("e1", 1000, Department.DEPARTMENT_1, Position.JUNIOR);
        Employee e2 = new Employee("e2", 1500, Department.DEPARTMENT_2, Position.MIDDLE);
        Employee e3 = new Employee("e3", 5000, Department.DEPARTMENT_3, Position.SENIOR);
        Employee e4 = new Employee("e4", 700, Department.DEPARTMENT_1, Position.JUNIOR);
        Employee e5 = new Employee("e5", 1700, Department.DEPARTMENT_2, Position.MIDDLE);
        Employee e6 = new Employee("e6", 4500, Department.DEPARTMENT_3, Position.SENIOR);

        Company company = new Company("Company1");

        company.addEmployee(e1);
        company.addEmployee(e2);
        company.addEmployee(e3);
        company.addEmployee(e4);
        company.addEmployee(e5);
        company.addEmployee(e6);

        Info info = new Info();

        System.out.println("Позиция сотрудника: ");
        info.checkPosition(e1);
        info.checkPosition(e2);
        info.checkPosition(e3);

        System.out.println("Средняя зп по департаменту: ");
        System.out.println(info.avgSalaryDep(company.getEmployees(), Department.DEPARTMENT_1));
        System.out.println(info.avgSalaryDep(company.getEmployees(), Department.DEPARTMENT_2));
        System.out.println(info.avgSalaryDep(company.getEmployees(), Department.DEPARTMENT_3));

        System.out.println("Минимальная зп по департаменту: ");
        System.out.println(info.maxSalary(company.getEmployees(), Department.DEPARTMENT_1));
        System.out.println(info.maxSalary(company.getEmployees(), Department.DEPARTMENT_2));
        System.out.println(info.maxSalary(company.getEmployees(), Department.DEPARTMENT_3));

        System.out.println("Максимальная зп по департаменту: ");
        System.out.println(info.minSalary(company.getEmployees(), Department.DEPARTMENT_1));
        System.out.println(info.minSalary(company.getEmployees(), Department.DEPARTMENT_2));
        System.out.println(info.minSalary(company.getEmployees(), Department.DEPARTMENT_3));

        System.out.println("Update сотрудников: ");
        System.out.println(e1);
        info.getPromotion(e1);
        System.out.println(e1);

        System.out.println();

        System.out.println(e2);
        info.getPromotion(e2);
        System.out.println(e2);

        System.out.println();

        System.out.println(e3);
        info.getPromotion(e3);
        System.out.println(e3);
    }
}
