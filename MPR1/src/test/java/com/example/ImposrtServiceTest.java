package com.example;

import com.example.exception.ApiException;
import com.example.exception.InvalidDataException;
import com.example.model.ImportSummary;
import com.example.service.EmployeeService;
import com.example.service.ImportService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImposrtServiceTest {
    private static EmployeeService employeeService;
    private static ImportService importService;
    private static ImportSummary importSummary;
    @BeforeAll
    public static void setUp() throws FileNotFoundException {
        employeeService = EmployeeService.getInstance();
        employeeService.removeAllEmployees();
        ImportService importService = new ImportService();
        importSummary = importService.importFromCsv("src/test/resources/employees_test.csv");

    }

    @AfterEach
    public void tearDown() {
        employeeService.removeAllEmployees();
    }
    @Test
    public void importFromCsvNumberImported() throws FileNotFoundException {
        assertEquals(importSummary.getNumberOfImportedEmployees(),20);
    }

}
