Nowa funkcjonalność: awans

Planning the structure and creating tests

Class: service/PromotionService

Methods:
- Position nextPosition(Position pos)
- boolean canBePromoted(Employee emp)
- void promote(Employee emp)					//promote into nextPosition
- void promote(Employee emp, Position pos)			//promote into given Position
- void giveRaise(Employee emp)					//default raise for position
- void giveRaise(Employee emp, double percentage)		//raise calculated by given percentage

created tests

RED - nextPosition
- written test code

GREEN - nextPosition
- changed CsvSource to include null correctly
- added method nextPosition to PromotionService
- written code for method nextPosition

RED - shouldReturnTrueForCanBePromoted
- written test code