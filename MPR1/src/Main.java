import Employees.Employee;
import Employees.EmployeeHandler;
import Employees.Position;

public class Main {
    public static void main(String[] args) {
        EmployeeHandler employeeHandler = new EmployeeHandler();

        Employee employee = Employee.Builder.newInstance()
                .setName("John")
                .setSurname("Doe")
                .setEmail("john@doe.com")
                .setCompanyName("Apple")
                .setPosition(Position.INTERN)
                .setSalary(1234)
                .build();

        employeeHandler.addEmployee(employee);

        Employee employee1 = Employee.Builder.newInstance()
                .setName("Aria")
                .setSurname("Doe")
                .setEmail("aria@doe.com")
                .setCompanyName("Apple")
                .setPosition(Position.INTERN)
                .build();
        employeeHandler.addEmployee(employee1);

        Employee employee2 = Employee.Builder.newInstance()
                .setName("Maria")
                .setSurname("Daria")
                .setEmail("maria@doe.com")
                .setCompanyName("Microsoft")
                .setPosition(Position.MANAGER)
                .build();
        employeeHandler.addEmployee(employee2);

        Employee employee3 = Employee.Builder.newInstance()
                .setName("John")
                .setSurname("Acrobat")
                .setEmail("john@acorbat.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(18500)
                .build();
        employeeHandler.addEmployee(employee3);

        //TESTS
        System.out.println("\nAll employees:");
        employeeHandler.printAllEmployees();

        System.out.println("\nApple employees:");
        employeeHandler.findByCompany("Apple").forEach(System.out::println);

        System.out.println("\nAlphabetically:");
        employeeHandler.sortBySurname().forEach(System.out::println);

        System.out.println("\nEmployee map by position:");
        employeeHandler.groupByPosition().forEach((pos, list) -> {
            System.out.println("\n" + pos + ":");
            list.forEach(emp -> System.out.println("  - " + emp));
        });

        System.out.println("\nPositions counted:");
        employeeHandler.countByPosition().forEach((pos, count) ->
                System.out.println(pos + ": " + count));

        System.out.println("\nAverage salary: " + employeeHandler.getAverageSalary());

        employeeHandler.findHighestSalary().ifPresent(emp ->
                System.out.println("\n Highest salary: " + emp));

    }
}