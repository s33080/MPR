package Employees;

public enum Position {
    PRESIDENT("president", 25000),
    VICEPRESIDENT("vicepresident",18000),
    MANAGER("manager",12000),
    PROGRAMMER("programmer",8000),
    INTERN("intern",3000);

    final String name;
    final double defaultSalary;
    Position(String name, double defaultSalary) {
        this.name = name;
        this.defaultSalary = defaultSalary;
    }

    @Override
    public String toString() {
        return name;
    }
}
