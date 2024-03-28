package com.epam.exercise;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * Main App
 * Responsible for managing the application running
 */
public class App {

    private static String CSV_FILE_PATH = "/data.csv";

    public static void main( String[] args ) throws IOException, URISyntaxException {

        var parser = new CSVParser(Objects.requireNonNull(App.class.getResourceAsStream(CSV_FILE_PATH)));

        var employees = parser.loadEmployeesFromCSV();

        var organizationStructureAnalyzer = new OrganizationStructureAnalyzer(employees);

        organizationStructureAnalyzer.findManagersWithLowSalary()
                .forEach(manager -> {
                    printSalaryText("low", manager.employeeData().firstName()+" "+manager.employeeData().lastName(), manager.employeeData().salary(), manager.minSalary());
                });

        organizationStructureAnalyzer.findManagersWithHighSalary()
                .forEach(manager -> {
                    printSalaryText("high", manager.employeeData().firstName()+" "+manager.employeeData().lastName(), manager.employeeData().salary(), manager.minSalary());
                });

        System.out.println("Employees with long reporting line:");
        organizationStructureAnalyzer.findEmployeesWithHighReportingLine()
                .forEach(employee -> {
                    System.out.println("Name: "+employee.firstName()+" "+employee.lastName()+", Length of reporting line (with the CEO): "+employee.lengthOfReportingLine());
                });

    }

    private static void printSalaryText(String type, String name, double salary, double expectedSalary){
        StringBuilder sb = new StringBuilder();

            sb.append("\nManager with ");
            sb.append(type);
            sb.append(" salary: ");
            sb.append(name);
            sb.append("\nCurrent vs Expected salary: ");
            sb.append(salary);
            sb.append(" <-> ");
            sb.append(expectedSalary);
            sb.append("\nDifferent is: ");
            sb.append(Math.abs(expectedSalary-salary));
            sb.append("\n");

        System.out.println(sb);
    }
}
