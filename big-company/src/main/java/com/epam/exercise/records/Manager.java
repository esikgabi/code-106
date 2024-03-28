package com.epam.exercise.records;

public record Manager(Employee employeeData, double averageOfSubordinatesSalary) {

    private static final double MIN_MANAGER_SALARY_PERCENTAGE = 1.2;
    private static final double MAX_MANAGER_SALARY_PERCENTAGE = 1.5;

    public double minSalary() {
        return (averageOfSubordinatesSalary * MIN_MANAGER_SALARY_PERCENTAGE);
    }

    public double maxSalary() {
        return averageOfSubordinatesSalary * MAX_MANAGER_SALARY_PERCENTAGE;
    }

}
