package com.epam.exercise;


import com.epam.exercise.records.CSVEmployee;
import com.epam.exercise.records.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the OrganizationStructureAnalyzer.
 */
public class OrganizationStructureAnalyzerTest {

    private OrganizationStructureAnalyzer analyzer;
    private List<CSVEmployee> csvEmployeeList = List.of(
            new CSVEmployee("1", "fName1", "lastName1", 120000),
            new CSVEmployee("2", "fName2", "lastName2", 150000, "1"),
            new CSVEmployee("3", "fName3", "lastName3", 90000, "2"),
            new CSVEmployee("4", "fName4", "lastName4", 800000, "3"),
            new CSVEmployee("5", "fName5", "lastName5", 40000, "2"),
            new CSVEmployee("6", "fName6", "lastName6", 23000, "4"),
            new CSVEmployee("7", "fName7", "lastName7", 12000, "6"),
            new CSVEmployee("8", "fName8", "lastName8", 10000, "7")

    );

    @BeforeEach
    public void setup() {
        analyzer = new OrganizationStructureAnalyzer(csvEmployeeList);
    }

    @Test
    public void findManagersWithLowSalaryTest()
    {
        var managersWithLowSalary = analyzer.findManagersWithLowSalary();
        assertEquals(2, managersWithLowSalary.size());

        assertArrayEquals(managersWithLowSalary.stream()
                    .map(man -> man.employeeData().id())
                    .toArray(), List.of("1", "3").toArray());
    }

    @Test
    public void findManagersWithHighSalaryTest()
    {
        var managersWithHighSalary = analyzer.findManagersWithHighSalary();
        assertEquals(3, managersWithHighSalary.size());
        assertArrayEquals(managersWithHighSalary.stream()
                .map(man -> man.employeeData().id())
                .toArray(), List.of("2", "4", "6").toArray());
    }

    @Test
    public void findEmployeesWithHighReportingLineTest(){
        var employeeWithHighReportingLine = analyzer.findEmployeesWithHighReportingLine();
        assertEquals(1, employeeWithHighReportingLine.size());
        assertArrayEquals(employeeWithHighReportingLine.stream()
                .map(Employee::id)
                .toArray(), List.of("8").toArray());
    }

}
