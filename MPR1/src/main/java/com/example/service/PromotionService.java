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
}
