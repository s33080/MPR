package service;

import exception.InvalidDataException;
import model.Employee;
import model.ImportSummary;
import model.Position;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ImportService {
    public ImportSummary ImportFromCsv(String filePath, EmployeeService employeeService) throws FileNotFoundException {
        //Available Enums
        HashSet<String> validPositions = new HashSet<String>();
        for(Position position : Position.values()) {
            validPositions.add(position.toString().toUpperCase());
        }

        ImportSummary importSummary = new ImportSummary();
        int numberOfImportedEmployees = 0;
        List<String> errorsDuringImport = new ArrayList<>();

        File file = new File(filePath);

        int lineNumber = 2;


        try(Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); //Ignores the first line
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.isEmpty()) {
                    lineNumber++;
                    continue;
                }
                line = line.trim();
                try{
                    String [] parts = line.split(",");
                    //Position and salary need to be converted from String into their types
                    if(parts.length != 6) {
                        errorsDuringImport.add("Not enough data on line " + lineNumber);
                        continue;
                    }
                    int salary = Integer.parseInt(parts[5]);
                    if(salary < 0) {
                        throw new InvalidDataException("Salary must be greater than 0");
                    }
                    if(!validPositions.contains(parts[4].toUpperCase())) {
                        throw new InvalidDataException("Position is not valid");
                    }
                    Position position = Position.valueOf(parts[4].toUpperCase());

                    Employee employee = Employee.Builder.newInstance()
                            .setName(parts[0])
                            .setSurname(parts[1])
                            .setEmail(parts[2])
                            .setCompanyName(parts[3])
                            .setPosition(position)
                            .setSalary(salary)
                            .build();
                    employeeService.addEmployee(employee);
                    numberOfImportedEmployees++;
                }
                catch(InvalidDataException e) {
                    errorsDuringImport.add(e.getMessage()+" on line "+lineNumber);
                }
                lineNumber++;
            }
        }
        catch (FileNotFoundException e) {
            errorsDuringImport.add(e.getMessage()+" on line "+lineNumber);
        }
        importSummary.setNumberOfImportedEmployees(numberOfImportedEmployees);
        importSummary.setErrorsDuringImport(errorsDuringImport);
        return importSummary;
    }
}