package Employees;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeHandler {
    private final Set<Employee> employees = new HashSet<>();

    public boolean addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            System.out.println("Employee already exists");
            return false;
        }
        employees.add(employee);
        return true;
    }

    public void printAllEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public List<Employee> findByCompany(String company){
        return employees.stream().filter(employee -> employee.getCompanyName().equals(company)).collect(Collectors.toList());
    }

    public List<Employee> sortBySurname(){
        return employees.stream().sorted(Comparator.comparing(Employee::getSurname)).collect(Collectors.toList());
    }

    public Map<Position, List<Employee>> groupByPosition(){
        return employees.stream().collect(Collectors.groupingBy(Employee::getPosition));
    }

    public Map<Position, Long> countByPosition(){
        return employees.stream().collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()));
    }

    public double getAverageSalary(){
        return employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
    }

    public Optional<Employee> findHighestSalary() {
        return employees.stream().max(Comparator.comparing(Employee::getSalary));
    }
}
