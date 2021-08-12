package Filehandler.Communally;

import Data.Communally.ConfirmedCasesAgeGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ConfirmedCasesAgeGroupFileHandler {
    public static ArrayList<ConfirmedCasesAgeGroup> getConfirmedCasesAgeGroupFromFile(String filename) {
        ArrayList<ConfirmedCasesAgeGroup> confirmedCasesAgeGroupList = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                ConfirmedCasesAgeGroup confirmedCasesAgeGroup = parseNumbers(line);
                confirmedCasesAgeGroupList.add(confirmedCasesAgeGroup);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return confirmedCasesAgeGroupList;
    }

    private static ConfirmedCasesAgeGroup parseNumbers(String inputString) {
        String[] tokens = inputString.split(";");
        return new ConfirmedCasesAgeGroup(tokens[0],tokens[1], tokens[2], tokens[3]);
    }
}

