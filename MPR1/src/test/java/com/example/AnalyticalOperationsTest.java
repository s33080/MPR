package com.example;

import com.example.model.Employee;
import com.example.model.Position;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyticalOperationsTest {
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
    public void noEmployeeTest() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Map<Position, List<Employee>> map = employeeService.groupByPosition();
        assertThat(map.get(Position.PRESIDENT), equalTo(null));
        assertEquals(employeeService.sortBySurname(), Collections.emptyList());
    }


    @Test
    public void sortBySurnameTest() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Aronia")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(10000)
                .build();
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("Zouse")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        Employee employee3 = Employee.Builder.newInstance()
                .setName("Peter")
                .setSurname("Parker")
                .setEmail("petes@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(23)
                .build();
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        List<Employee> expected = List.of(employee1, employee3, employee2); //a->p->z
        assertEquals(employeeService.sortBySurname(), expected);
    }
    @Test
    public void findByCompanyTest() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Aronia")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(10000)
                .build();
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("Zouse")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        Employee employee3 = Employee.Builder.newInstance()
                .setName("Peter")
                .setSurname("Parker")
                .setEmail("petes@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(23)
                .build();
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        assertThat(employeeService.findByCompany("Apple"), containsInAnyOrder(employee1, employee3));
    }
    @Test
    public void groupByPositionTest() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Aronia")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(10000)
                .build();
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("Zouse")
                .setEmail("hospital@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(20000)
                .build();
        Employee employee3 = Employee.Builder.newInstance()
                .setName("Peter")
                .setSurname("Parker")
                .setEmail("petes@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(23)
                .build();
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        Employee employee4 = Employee.Builder.newInstance()
                .setName("Iron")
                .setSurname("Man")
                .setEmail("ronin@example.com")
                .setCompanyName("Bwhatever")
                .setPosition(Position.PRESIDENT)
                .setSalary(210000)
                .build();
        employeeService.addEmployee(employee4);
        Employee employee5 = Employee.Builder.newInstance()
                .setName("Donald")
                .setSurname("Trump")
                .setEmail("maga@example.com")
                .setCompanyName("MAGA")
                .setPosition(Position.PRESIDENT)
                .setSalary(21370)
                .build();
        employeeService.addEmployee(employee5);
        Map<Position, List<Employee>> map = employeeService.groupByPosition();
        assertThat(map.get(Position.PRESIDENT), hasSize(2));
    }
    @Test
    public void countByPositionTest() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee employee6 = Employee.Builder.newInstance()
                .setName("Momma")
                .setSurname("Mia")
                .setEmail("lovey@example.com")
                .setCompanyName("MMMM")
                .setPosition(Position.MANAGER)
                .setSalary(210000)
                .build();
        employeeService.addEmployee(employee6);
        Employee employee7 = Employee.Builder.newInstance()
                .setName("Sofia")
                .setSurname("Trump")
                .setEmail("manger@example.com")
                .setCompanyName("MAGA")
                .setPosition(Position.MANAGER)
                .setSalary(21370)
                .build();
        employeeService.addEmployee(employee7);
        Employee employee8 = Employee.Builder.newInstance()
                .setName("Sofia")
                .setSurname("Mintu")
                .setEmail("mint@example.com")
                .setCompanyName("help")
                .setPosition(Position.INTERN)
                .setSalary(1000)
                .build();
        employeeService.addEmployee(employee8);
        Map<Position, Long> map = employeeService.countByPosition();
        assertThat(map.get(Position.MANAGER), equalTo(2L));
    }
}
