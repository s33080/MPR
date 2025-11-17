package com.example;

import com.example.model.Employee;
import com.example.model.Position;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.function.Predicate.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FinancialFinancialStatisticsTest {
    private static EmployeeService employeeService;
    @BeforeAll
    public static void setUp() {
        employeeService = EmployeeService.getInstance();
        employeeService.removeAllEmployees();
    }

    @AfterEach
    public void tearDown() {
        employeeService.removeAllEmployees();
    }
    @Test
    public void averageSalaryTest() {
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(10000)
                .build();
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        EmployeeService employeeService = EmployeeService.getInstance();
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        assertEquals(15000, employeeService.getAverageSalary(), 0.001);
    }
    @Test
    public void highestSalaryTest() {
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(10000)
                .build();
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        EmployeeService employeeService = EmployeeService.getInstance();
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        assertEquals(employeeService.findHighestSalary(), "Employee: "+ employee2.getName() + " "+ employee2.getSurname() + ", " + employee2.getSalary());
    }
    @Test
    public void shouldReturnFirstAddedEmployeeWithHighestSalaryWhenMultiple() {
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        Employee employee3 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("chygeu@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(20000)
                .build();
        Employee employee4 = Employee.Builder.newInstance()
                .setName("Mary")
                .setSurname("Poppins")
                .setEmail("hrvbjeyf@example.com")
                .setCompanyName("Mhaw")
                .setPosition(Position.MANAGER)
                .setSalary(20000)
                .build();
        EmployeeService employeeService = EmployeeService.getInstance();
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        employeeService.addEmployee(employee4);
        assertEquals(employeeService.findHighestSalary(), "Employee: "+ employee2.getName() + " "+ employee2.getSurname() + ", " + employee2.getSalary());
    }
    
    @Test
    public void oneEmployeeTest() {
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        EmployeeService employeeService = EmployeeService.getInstance();
        employeeService.addEmployee(employee2);

        assertThat(employeeService.sortBySurname(), hasSize(1));
    }
    @Test
    public void salaryConsistencyTest() {
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        Employee employee3 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("chygeu@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(20000)
                .build();
        Employee employee4 = Employee.Builder.newInstance()
                .setName("Mary")
                .setSurname("Poppins")
                .setEmail("hrvbjeyf@example.com")
                .setCompanyName("Mhaw")
                .setPosition(Position.MANAGER)
                .setSalary(20)
                .build();
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        employeeService.addEmployee(employee4);
        List<Employee> employees = employeeService.validateSalaryConsistency();
        assertEquals(employees.size(), 1);

    }
}
