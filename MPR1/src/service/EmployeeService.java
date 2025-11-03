package service;

import model.CompanyStatistics;
import model.Employee;
import model.Position;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private static final EmployeeService instance = new EmployeeService();
    private EmployeeService() {}
    public static EmployeeService getInstance() {
        return instance;
    }

    private final Set<Employee> employees = new HashSet<>();

    public boolean addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            System.out.println("Employee already exists");
            return false;
        }
        employees.add(employee);
        return true;
    }

    public void printAllEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public List<Employee> findByCompany(String company){
        return employees.stream().filter(employee -> employee.getCompanyName().equals(company)).collect(Collectors.toList());
    }

    public List<Employee> sortBySurname(){
        return employees.stream().sorted(Comparator.comparing(Employee::getSurname)).collect(Collectors.toList());
    }

    public Map<Position, List<Employee>> groupByPosition(){
        return employees.stream().collect(Collectors.groupingBy(Employee::getPosition));
    }

    public Map<Position, Long> countByPosition(){
        return employees.stream().collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()));
    }

    public double getAverageSalary(){
        return employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
    }

    public Optional<Employee> findHighestSalary() {
        return employees.stream().max(Comparator.comparing(Employee::getSalary));
    }

    public List<Employee> validateSalaryConsistency(){
        List<Employee> WrongedEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            if (employee.getSalary() < employee.getPosition().getDefaultSalary()){
                WrongedEmployees.add(employee);
            }
        });
        return WrongedEmployees;
    }

    public Map<String, CompanyStatistics> getCompanyStatistics() {
        Map<String, CompanyStatistics> companyStatisticsHashMap = new HashMap<>();
        for (Employee employee : employees) {
            String companyName = employee.getCompanyName();
            if(!companyStatisticsHashMap.containsKey(companyName)){
                //Creates new CompanyStatistics instance and puts it in the HashMap with key being companyName
                CompanyStatistics companyStatistics = new CompanyStatistics();
                companyStatistics.setCompanyName(companyName);
                companyStatistics.setEmployeeCount(1);
                companyStatistics.setAverageSalary(employee.getSalary());
                companyStatistics.setHighestSalary(employee.getSalary());
                companyStatistics.setEmployeeWithHighestSalary(employee.getName()+" "+employee.getSurname());
                companyStatisticsHashMap.put(companyName, companyStatistics);
            }
            else{
                //there is a matching key
                CompanyStatistics companyStatistics = companyStatisticsHashMap.get(companyName);
                companyStatistics.setEmployeeCount(companyStatistics.getEmployeeCount()+1);
                companyStatistics.setAverageSalary( //new average: ( (oldAverage*oldEmployeeCount)+newHireSalary )/(oldEmployeeCount + 1)
                        (companyStatistics.getAverageSalary()*(companyStatistics.getEmployeeCount()-1) + employee.getSalary())/companyStatistics.getEmployeeCount()
                );
                if(companyStatistics.getHighestSalary() < employee.getSalary()){
                    companyStatistics.setHighestSalary(employee.getSalary());
                    companyStatistics.setEmployeeWithHighestSalary(employee.getName()+" "+employee.getSurname());
                }
            }
        }
        return companyStatisticsHashMap;
    }
}