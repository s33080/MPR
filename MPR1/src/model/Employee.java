package model;

import java.util.Objects;

public class Employee {
    private String name;
    private String surname;
    private String email;
    private String companyName;
    private Position position;
    private double salary;

    //BUILDER
    public static class Builder {
        private String name;
        private String surname;
        private String email;
        private String companyName;
        private Position position;
        private double salary;

        public static Builder newInstance() {
            return new Builder();
        }
        private Builder() {}

        //setters
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }
        public Builder setPosition(Position position) {
            this.position = position;
            if(this.salary == 0){
                this.salary = position.defaultSalary;
            }
            return this;
        }
        public Builder setSalary(int salary) {
            this.salary = salary;
            return this;
        }
        public Employee build() {
            Employee employee = new Employee();
            employee.name = name;
            employee.surname = surname;
            employee.email = email;
            employee.companyName = companyName;
            employee.position = position;
            employee.salary = salary;
            return employee;
        }

    }

    //GETTERS
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String getCompanyName() {
        return companyName;
    }
    public double getSalary() {
        return salary;
    }
    public Position getPosition() {return position;}

    @Override
    public String toString() {
        return "Name: "+this.getName()
                +"\tSurname: "+this.getSurname()
                +"\tEmail: "+this.getEmail()
                +"\tCompany Name: "+this.getCompanyName()
                +"\tPosition: "+this.position
                +"\tSalary: "+this.getSalary();
    }

    @Override
    public boolean equals(Object o) {
        Employee employee = (Employee) o;
        return Objects.equals(email , employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}