package com.epam.exercise;

import com.epam.exercise.records.CSVEmployee;
import com.epam.exercise.records.Employee;
import com.epam.exercise.records.Manager;

import java.util.List;
import java.util.Objects;

public class OrganizationStructureAnalyzer {

    private final List<Employee> employees;
    private final List<Manager> managers;

    public static final int MAX_MANAGER_LINE_COUNT = 4;

    public OrganizationStructureAnalyzer(List<CSVEmployee> csvEmployees){
        Objects.requireNonNull(csvEmployees);

        this.employees = csvEmployees.stream()
                .map(csvEmployee -> new Employee(csvEmployee, reportingLine(csvEmployees, csvEmployee.id())))
                .toList();

        var managerIds = employees.stream()
                .map(Employee::managerId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();


        managers = employees.stream()
                .filter(emp -> managerIds.contains(emp.id()))
                .map(emp -> new Manager(emp, getSubordinatesSalaryAvg(emp.id())))
                .toList();

    }

    public List<Manager> findManagersWithLowSalary(){
        return managers.stream()
                .filter(man -> man.employeeData().salary() < man.minSalary() )
                .toList();
    }

    public List<Manager> findManagersWithHighSalary(){
        return managers.stream()
                .filter(man -> man.employeeData().salary() > man.maxSalary() )
                .toList();
    }

    public List<Employee> findEmployeesWithHighReportingLine(){
        return employees.stream()
                .filter(e -> e.lengthOfReportingLine() > MAX_MANAGER_LINE_COUNT)
                .toList();
    }

    public double getSubordinatesSalaryAvg(String managerId){
        var subordinates = employees.stream()
                .filter(e -> e.managerId()!=null && e.managerId().equals(managerId))
                .toList();

        var salarySum = subordinates.stream()
                .mapToDouble(Employee::salary)
                .sum();

        return salarySum/subordinates.size();
    }

    public int reportingLine(List<CSVEmployee> employees, String employeeId){

        var emp = employees.stream()
                .filter(e -> e.id() != null && e.id().equals(employeeId))
                .findFirst();

        var round = 0;

        while(emp.isPresent() && emp.get().managerId() != null) {
            var managerId = emp.get().managerId();
            emp = employees.stream()
                    .filter(e -> e.id() != null && e.id().equals(managerId))
                    .findFirst();
            round++;
        }

        return round;
    }

}
