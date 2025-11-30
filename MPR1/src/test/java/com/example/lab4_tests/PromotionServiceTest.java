package com.example.lab4_tests;

import com.example.model.Position;
import com.example.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionServiceTest {
    //nextPosition
    @ParameterizedTest
    @CsvSource({
            "INTERN, PROGRAMMER",
            "PROGRAMMER, MANAGER",
            "PRESIDENT, null"
    })
    public void nextPositionTest(Position position, Position expected) {
        //Arrange
        PromotionService promotionService = new PromotionService();

        //Act
        Position nextPosition = promotionService.nextPosition(position);

        //Assert
        assertEquals(expected, nextPosition);
    }
    //canBePromoted
    @Test
    public void shouldReturnTrueForCanBePromoted() {

    }
    @Test
    public void shouldReturnFalseForCanNotBePromoted() {

    }
    //promote
    @Test
    public void positionShouldNotBeNullAfterPromotion() {

    }
    @Test
    public void positionShouldNotBeTheSameAfterPromotion() {

    }
    @Test
    public void positionShouldBeManagerAfterPromotion() {

    }
    //giveRaise
    @Test
    public void salaryShouldBeDefaultAfterPromotionIntoProgrammer(){

    }
    @Test
    public void salaryShouldBeRaisedByPercentage(){}
    @Test
    public void salaryShouldBeTheSameAsBeforeIfTooMuch(){

    }
}
