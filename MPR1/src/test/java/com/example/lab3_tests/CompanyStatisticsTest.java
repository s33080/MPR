package com.example.lab3_tests;

import com.example.model.CompanyStatistics;
import com.example.model.Employee;
import com.example.model.Position;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyStatisticsTest {
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
    public void EmployeeService_CompanyStatistics_IntegrationTest() {
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
        Map<String, CompanyStatistics> map = employeeService.getCompanyStatistics();
        assertFalse(map.isEmpty());
    }
    @Test
    public void testCompanyStatisticsToStringTest() {
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
        Map<String, CompanyStatistics> map = employeeService.getCompanyStatistics();
        System.out.println(map.get("Apple"));
        assertEquals("Apple\t\tNumber of employees:\t 2\t\tAverage salary:\t 5011.5\t\tHighest salary:\t 10000.0, Ala Aronia", map.get("Apple").toString());

    }
    @Test
    @DisplayName("Should not add null employee to CompanyStatistics")
    public void nullEmployeeInCompanyStatisticsTest() {
        Employee employee1 = null;
        employeeService.addEmployee(employee1);
        Map<String, CompanyStatistics> map = employeeService.getCompanyStatistics();
        assertEquals(0, map.size());
    }
}