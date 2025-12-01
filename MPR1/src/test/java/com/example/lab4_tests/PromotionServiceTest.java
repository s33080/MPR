package com.example.lab4_tests;

import com.example.model.Employee;
import com.example.model.Position;
import com.example.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionServiceTest {
    //nextPosition
    @ParameterizedTest
    @CsvSource(
            value ={
                "INTERN, PROGRAMMER",
                "PROGRAMMER, MANAGER",
                "PRESIDENT, NULL"
            },
            nullValues = "NULL"
    )
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
        //Arrange
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.PROGRAMMER)
                .build();


        //Assert
        assertThat(promotionService.canBePromoted(employee))    //Asercje na wartościach boolean
                .isTrue();
    }
    @Test
    public void shouldReturnFalseForCanNotBePromoted() {
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.PRESIDENT)
                .build();
        assertThat(promotionService.canBePromoted(employee))    //Asercje na wartościach boolean
                .isFalse();
    }
    //promote
    @Test
    public void positionShouldNotBeNullAfterPromotion() {
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.PROGRAMMER)
                .build();

        promotionService.promote(employee);

        assertThat(employee.getPosition())
                .isNotNull();
    }
    @Test
    public void positionShouldNotBeTheSameAfterPromotion() {
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.PROGRAMMER)
                .build();

        promotionService.promote(employee);

        assertThat(employee.getPosition())
                .isNotSameAs(Position.PROGRAMMER);
    }
    @Test
    public void positionShouldBeManagerAfterPromotion() {
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.PROGRAMMER)
                .build();

        promotionService.promote(employee);

        assertThat(employee.getPosition())
                .isEqualTo(Position.MANAGER);
    }
    //giveRaise
    @Test
    public void salaryShouldBeDefaultAfterPromotionIntoProgrammer(){
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.INTERN)
                .build();

        promotionService.promote(employee);

        assertThat(employee.getSalary())
                .isEqualTo(Position.PROGRAMMER.getDefaultSalary());
    }
    @Test
    public void salaryShouldBeRaisedByPercentage(){
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.INTERN)
                .build();

        promotionService.promote(employee, 220);

        assertThat(employee.getSalary())
                .isGreaterThan(Position.PROGRAMMER.getDefaultSalary())
                .isLessThan(Position.MANAGER.getDefaultSalary());
    }
    @Test
    public void salaryDefaultIfTooMuch(){
        PromotionService promotionService = new PromotionService();
        Employee employee = Employee.Builder.newInstance()
                .setName("Jane")
                .setSurname("Doe")
                .setEmail("jane.doe@email.com")
                .setCompanyName("AAA")
                .setPosition(Position.INTERN)
                .build();

        promotionService.promote(employee, 500);

        assertThat(employee.getSalary())
                .isEqualTo(Position.PROGRAMMER.getDefaultSalary());
    }
}
