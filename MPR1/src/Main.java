import model.CompanyStatistics;
import model.Employee;
import model.ImportSummary;
import service.EmployeeService;
import model.Position;
import service.ImportService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        EmployeeService employeeService = new EmployeeService();
        EmployeeService employeeService = EmployeeService.getInstance();

        //TESTS
        ImportService importService = new ImportService();
        ImportSummary importSummary;
        importSummary = importService.ImportFromCsv("src\\resources\\employees.csv", employeeService);

        System.out.println("\nAfter CSV import:");
        employeeService.printAllEmployees();

        System.out.println(importSummary.getNumberOfImportedEmployees());
        System.out.println(importSummary.getErrorsDuringImport());
    }
}