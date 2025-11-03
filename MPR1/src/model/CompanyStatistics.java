package model;

import java.util.List;

public class CompanyStatistics {
    private String companyName;
    private int employeeCount;
    private double averageSalary;
    private double highestSalary;
    private String employeeWithHighestSalary;


    public CompanyStatistics() {}
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public int getEmployeeCount() {
        return employeeCount;
    }
    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
    public double getAverageSalary() {
        return averageSalary;
    }
    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
    public double getHighestSalary() {
        return highestSalary;
    }
    public void setHighestSalary(double highestSalary) {
        this.highestSalary = highestSalary;
    }
    public String getEmployeeWithHighestSalary() {
        return employeeWithHighestSalary;
    }
    public void setEmployeeWithHighestSalary(String employeeWithHighestSalary) {
        this.employeeWithHighestSalary = employeeWithHighestSalary;
    }
    @Override
    public String toString() {
        return getCompanyName() + "\t\tNumber of employees:\t " + getEmployeeCount() + "\t\tAverage salary:\t " + getAverageSalary() + "\t\tHighest salary:\t " + getHighestSalary() + ", "+getEmployeeWithHighestSalary();
    }
}
