package com.epam.exercise;

import com.epam.exercise.records.CSVEmployee;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVParser {

    private static final String REGEX = ",";

    private final URI fileURI;

    public CSVParser(URI fileURI){
        this.fileURI = fileURI;
    }

    public List<CSVEmployee> loadEmployeesFromCSV() throws IOException {
        return Files.lines(Paths.get(fileURI))
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
