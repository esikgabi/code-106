package com.epam.exercise;

import com.epam.exercise.records.CSVEmployee;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the CSVParser.
 */
public class CSVParserTest {

    private static final String CSV_FILE_PATH = "/test-data.csv";
    private final CSVParser parser = new CSVParser(Objects.requireNonNull(CSVParserTest.class.getResourceAsStream(CSV_FILE_PATH)));

    @Test
    public void loadEmployeesFromCSVTest() throws IOException {
        var employees = parser.loadEmployeesFromCSV();
        assertEquals(3, employees.size());
        assertArrayEquals(employees.stream()
                .map(CSVEmployee::id)
                .toArray(), List.of("123", "124", "125").toArray());
    }
}
