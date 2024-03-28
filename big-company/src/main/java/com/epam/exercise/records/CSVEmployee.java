package com.epam.exercise.records;

public record CSVEmployee(String id, String firstName, String lastName, long salary, String managerId) {

    public CSVEmployee(String id, String firstName, String lastName, long salary){
        this(id, firstName, lastName, salary, null);
    }

}
