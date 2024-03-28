package com.epam.exercise;

import com.epam.exercise.records.CSVEmployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * CSV Parser
 * Responsible for parsing the CSV file and produce CSVEmployee collection
 */
public class CSVParser {

    private static final String REGEX = ",";

    private final InputStream inputStream;

    public CSVParser(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<CSVEmployee> loadEmployeesFromCSV() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.lines()
                .skip(1)
                .map(line -> createEmployee(line.split(REGEX)))
                .toList();
    }

    private CSVEmployee createEmployee(String[] attributes){
        if(attributes.length == 4){
            return new CSVEmployee(attributes[0], attributes[1], attributes[2], Long.parseLong(attributes[3]));
        } else if (attributes.length > 4) {
            return new CSVEmployee(attributes[0], attributes[1], attributes[2], Long.parseLong(attributes[3]), attributes[4]);
        }
        return null;
    }

}
