package com.example.model;

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

        //setters for Builder
        public Builder setName(String name) {
            if(name==null){
                throw new IllegalArgumentException("Name cannot be null");
            }
            this.name = name;
            return this;
        }
        public Builder setSurname(String surname) {
            if(surname==null){
                throw new IllegalArgumentException("Surname cannot be null");
            }
            this.surname = surname;
            return this;
        }
        public Builder setEmail(String email) {
            if(email==null){
                throw new IllegalArgumentException("Email cannot be null");
            }
            if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
                throw new IllegalArgumentException("Invalid email format");
            }
            this.email = email;
            return this;
        }
        public Builder setCompanyName(String companyName) {
            if(companyName==null){
                throw new IllegalArgumentException("CompanyName cannot be null");
            }
            this.companyName = companyName;
            return this;
        }
        public Builder setPosition(Position position) {
            if(position==null){
                throw new IllegalArgumentException("Position cannot be null");
            }
            this.position = position;
            if(this.salary == 0){
                this.salary = position.defaultSalary;
            }
            return this;
        }
        public Builder setSalary(double salary) {
            if(salary < 0){
                if(position != null){
                    this.salary = position.defaultSalary;
                }
                else{
                    throw new IllegalArgumentException("Salary should be a positive number, could not set to default salary because position has not been set");
                }
            }
            else{
                this.salary = salary;
            }
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

    //SETTERS
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

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