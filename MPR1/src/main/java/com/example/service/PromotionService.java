package com.example.service;

import com.example.model.Employee;
import com.example.model.Position;

public class PromotionService {
    public Position nextPosition(Position position) {
        if(position == Position.INTERN){
            return Position.PROGRAMMER;
        }
        if(position == Position.PROGRAMMER){
            return Position.MANAGER;
        }
        if(position == Position.MANAGER) {
            return Position.VICEPRESIDENT;
        }
        if(position == Position.VICEPRESIDENT) {
            return Position.PRESIDENT;
        }
        else{
            return null;
        }
    }

    public boolean canBePromoted (Employee employee) {
        if(nextPosition(employee.getPosition()) != null) {
            return true;
        }
        return false;
    }

    public void promote(Employee employee) {
        if(canBePromoted(employee)) {
            employee.setPosition(nextPosition(employee.getPosition()));
            giveRaise(employee, 0);
        }
        else {
            throw new IllegalArgumentException("Cannot promote employee");
        }
    }
    public void promote(Employee employee, double percentage) {
        if(canBePromoted(employee)) {
            employee.setPosition(nextPosition(employee.getPosition()));
            giveRaise(employee, percentage);
        }
    }
    public void giveRaise(Employee employee, double percentage) {
        Position position = employee.getPosition();
        if(percentage == 0) {
            employee.setSalary(position.getDefaultSalary());
        }
        if(employee.getSalary() < position.getDefaultSalary()) {
            double newSalary = employee.getSalary() + (percentage/100)*employee.getSalary();
            if(newSalary <= position.getDefaultSalary()) { //if it's less than minimum for this position
                employee.setSalary(position.getDefaultSalary());
                System.out.println("a");
            }
            if(newSalary >= nextPosition(position).getDefaultSalary()) { //if its equal or more than next position's
                employee.setSalary(position.getDefaultSalary());
                return;
            }
            System.out.println("c");
            employee.setSalary(newSalary);
        }
    }
}
