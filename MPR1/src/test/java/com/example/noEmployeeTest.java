package com.example;

import com.example.model.Employee;
import com.example.model.Position;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class noEmployeeTest {
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
        Map<Position, List<Employee>> map = employeeService.groupByPosition();
        assertThat(map.get(Position.PRESIDENT), equalTo(null));
        assertEquals(employeeService.sortBySurname(), Collections.emptyList());
    }

    @Test
    public void oneEmployeeTest() {
        assertEquals(employeeService.findHighestSalary(), "No employees found");
    }
}
