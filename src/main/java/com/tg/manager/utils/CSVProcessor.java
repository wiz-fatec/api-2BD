package com.tg.manager.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVProcessor {
    public static List<List<String>> readCSVToListOfLists(String filePath) {
        List<List<String>> listOfLists = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : csvParser) {
                List<String> innerList = new ArrayList<>();
                for (String value : record) {
                    innerList.add(value);
                }
                listOfLists.add(innerList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfLists;
    }
}
