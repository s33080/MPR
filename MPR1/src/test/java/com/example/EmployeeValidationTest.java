package com.example;

import com.example.exception.ApiException;
import com.example.model.Employee;
import com.example.model.Position;
import com.example.service.ApiService;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeValidationTest {
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
    public void addNewEmployeeToEmployeeService() {
        Employee employee1 = Employee.Builder.newInstance()
                .setName("Ala")
                .setSurname("Kot")
                .setEmail("whatever@example.com")
                .setCompanyName("Apple")
                .setPosition(Position.PROGRAMMER)
                .setSalary(12000)
                .build();
        EmployeeService employeeService = EmployeeService.getInstance();
        employeeService.addEmployee(employee1);
        assertThat(employeeService.sortBySurname(), contains(employee1));
    }
    @Test
    public void employeeIsNotAddedIfAlreadyExits() {
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
        EmployeeService employeeService = EmployeeService.getInstance();
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        List<Employee> employees = employeeService.sortBySurname();
        assertThat(employees, hasSize(1));
//        for (Employee employee : employees) {
//            assertThat(employee.getName(), not(is(employee2.getName())));
//        }
    }
    @Test
    public void employeeIsAddedThroughAPI() {
        //String path = "https://mocki.io/v1/73d3edf1-0d18-4b12-9b41-c4c6aaea372a";
        //Always have a JSON object array, not a single JSON object
        String path = "https://mocki.io/v1/95298227-5de6-40bd-9f37-d8ceae36c0ee";
        ApiService apiService = new ApiService();
        List<Employee> emps = apiService.fetchEmployeesFromApi(path);
        assertThat(emps, hasSize(1));
    }
    @Test
    public void throwsAPIExceptionIfDataWrong() {
        String path = "https://mocki.io/v1/98ab2e5d-a6c3-463a-b16d-5c035d203732";
        ApiService apiService = new ApiService();
//        assertThrows(apiService.fetchEmployeesFromApi(path), ApiException);
        assertThrows(ApiException.class, () -> apiService.fetchEmployeesFromApi(path));
    }
}
