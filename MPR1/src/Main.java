import model.CompanyStatistics;
import model.Employee;
import model.ImportSummary;
import service.ApiService;
import service.EmployeeService;
import model.Position;
import service.ImportService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        EmployeeService employeeService = new EmployeeService();
        EmployeeService employeeService = EmployeeService.getInstance();

        //TESTS
        System.out.println("CSV TEST");
        ImportService importService = new ImportService();
        ImportSummary importSummary;
        importSummary = importService.ImportFromCsv("src\\resources\\employees.csv");
        System.out.println("\nAfter CSV import:");
        employeeService.printAllEmployees();

        System.out.println("\nEmployees imported:"+importSummary.getNumberOfImportedEmployees());
        System.out.println("Errors: "+importSummary.getErrorsDuringImport());


        System.out.println("\n\n");

        System.out.println("API TEST");
        System.out.println("Returned List:");
        ApiService apiService = new ApiService();
        List<Employee> emps = apiService.fetchEmployeesFromApi("https://mocki.io/v1/18dfb986-b83b-476c-b739-1787a8f8c277");
        emps.forEach(emp -> {
            System.out.println(emp.toString());
        });

//        for (Employee emp : emps) {
//            employeeService.addEmployee(emp);
//        }
        //employeeService.printAllEmployees();

        System.out.println("\n\n");

        System.out.println("ANALYTICAL OPERATIONS TEST");

        System.out.println("\nList of employees with unfair salaries:");

        List<Employee> wrongedEmployees = new ArrayList<>();
        wrongedEmployees = employeeService.validateSalaryConsistency();
        for (Employee emp : wrongedEmployees) {
            System.out.println(emp.toString());
        }

        System.out.println("\nCompany statistics:");

        Map<String, CompanyStatistics> companyMap = new HashMap<>();
        companyMap = employeeService.getCompanyStatistics();
        for (Map.Entry<String, CompanyStatistics> entry : companyMap.entrySet()) {
//            System.out.println(entry.getKey());
            CompanyStatistics companyStatistics = entry.getValue();
            System.out.println(companyStatistics);
        }



    }
}