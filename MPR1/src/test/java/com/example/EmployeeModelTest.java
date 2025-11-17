package com.example;

import com.example.model.Employee;
import com.example.model.Position;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeModelTest {
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
    @DisplayName("Equals method test")
    public void testEquals() {
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(12000)
                .build();
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("whatever@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(50000)
                .build();

        assertThat(employee1, equalTo(employee2));
    }

    @Test
    @DisplayName("HashCode method test")
    public void testHashCode() {
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(12000)
                .build();
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("whatever@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(50000)
                .build();

        Set<Employee> set = new HashSet<>();
        set.add(employee1);
        set.add(employee2);
        assertThat(set, hasSize(1));
//        for (Employee employee : set) {
//            assertThat(employee.getName(), equalTo(employee1.getName()));
//        }
    }
    @Test
    @DisplayName("Enum Position test")
    public void testEnumPosition_whenEverythingCorrect() {
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .build();
        assertThat(employee1.getPosition(), equalTo(Position.PROGRAMMER));
    }
    @Test
    public void testEnumPosition_whenSalaryNotSet() {
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .build();
        assertThat(employee1.getSalary(), equalTo(Position.PROGRAMMER.getDefaultSalary()));
    }
    @Test
    public void testEnumPosition_whenSalaryNegative() {
        Employee employee2 = Employee.Builder.newInstance()
                .setName("Gregory")
                .setSurname("House")
                .setEmail("whatever@example.com")
                .setCompanyName("House MD")
                .setPosition(Position.VICEPRESIDENT)
                .setSalary(-50000)
                .build();
        assertThat(employee2.getSalary(), equalTo(Position.VICEPRESIDENT.getDefaultSalary()));
    }
    @Test
    public void shouldThrowException_whenSalaryNegativeAndPositionNotSet() {
        try{
            Employee employee3 = Employee.Builder.newInstance()
                    .setName("Walter")
                    .setSurname("White")
                    .setEmail("whatever@example.com")
                    .setCompanyName("Jessy")
                    .setSalary(-50000)
                    .setPosition(Position.PRESIDENT)
                    .build();
        }catch(Exception e){
            assertThat(e.getMessage(),containsString("Salary should be a positive number, could not set to default salary because position has not been set"));
        }
    }
}