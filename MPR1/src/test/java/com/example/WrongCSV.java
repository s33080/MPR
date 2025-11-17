//package com.example;
//
//import com.example.exception.InvalidDataException;
//import com.example.model.ImportSummary;
//import com.example.service.EmployeeService;
//import com.example.service.ImportService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileNotFoundException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class WrongCSV {
//    private static EmployeeService employeeService;
//    private static ImportService importService;
//    private static ImportSummary importSummary;
//    @BeforeAll
//    public static void setUp() throws FileNotFoundException {
//        employeeService = EmployeeService.getInstance();
//        employeeService.removeAllEmployees();
//        ImportService importService = new ImportService();
//        importSummary = importService.importFromCsv("src/test/resources/vwjdve.csv");
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//        employeeService.removeAllEmployees();
//    }
//    @Test
//    public void InvalidDataTest() throws FileNotFoundException {
//
//    }
//
//}
