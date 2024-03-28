package com.epam.exercise.records;

public record Employee(String id, String firstName, String lastName, long salary, String managerId, int lengthOfReportingLine) {

    public Employee(CSVEmployee csvEmployee, int lengthOfReportingLine){
        this(csvEmployee.id(), csvEmployee.firstName(), csvEmployee.lastName(), csvEmployee.salary(), csvEmployee.managerId(), lengthOfReportingLine);
    }
}
