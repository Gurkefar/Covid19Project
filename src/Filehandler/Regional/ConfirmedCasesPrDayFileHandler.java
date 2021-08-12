package Filehandler.Regional;

import Data.Regional.ConfirmedCasesPrDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ConfirmedCasesPrDayFileHandler {
    public static ArrayList<ConfirmedCasesPrDay> getConfirmedCasesPrDayFromFile(String filename) {
        ArrayList<ConfirmedCasesPrDay> confirmedCasesPrDays = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                ConfirmedCasesPrDay confirmedCasesPrDay1 = parseNumbers(line);
                confirmedCasesPrDays.add(confirmedCasesPrDay1);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return confirmedCasesPrDays;
    }

    private static ConfirmedCasesPrDay parseNumbers(String inputString) {
        String[] tokens = inputString.split(";");
        return new ConfirmedCasesPrDay(tokens[0],tokens[1], tokens[2]);
    }
}