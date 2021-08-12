package Filehandler.Communally;

import Data.Communally.Hospitalized;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HospitalizedFileHandler {
    public static ArrayList<Hospitalized> getAgeGroupFromFile(String filename) {
        ArrayList<Hospitalized> ageGroups = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Hospitalized hospitalized = parseNumbers(line);
                ageGroups.add(hospitalized);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return ageGroups;
    }

    private static Hospitalized parseNumbers(String inputString) {
        String[] tokens = inputString.split(";");
        return new Hospitalized(tokens[0],tokens[1]);
    }
}
