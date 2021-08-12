package Filehandler.Regional;

import Data.Regional.DeadPrDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DeadPrDayFileHandler {
    public static ArrayList<DeadPrDay> getDeadPrDayFromFile(String filename) {
        ArrayList<DeadPrDay> deadPrDays = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                DeadPrDay deadPrDay1 = parseNumbers(line);
                deadPrDays.add(deadPrDay1);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return deadPrDays;
    }

    private static DeadPrDay parseNumbers(String inputString) {
        String[] tokens = inputString.split(";");
        return new DeadPrDay(tokens[0],tokens[1], tokens[2], tokens[3]);
    }
}