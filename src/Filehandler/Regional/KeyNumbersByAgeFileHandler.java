package Filehandler.Regional;

import Data.Regional.KeyNumbersByAge;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class KeyNumbersByAgeFileHandler {
    public static ArrayList<KeyNumbersByAge> getKeyNumbersByAgeFromFile(String filename) {
        ArrayList<KeyNumbersByAge> keyNumbersByAge = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                KeyNumbersByAge deathCount = parseNumbers(line);
                keyNumbersByAge.add(deathCount);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return keyNumbersByAge;
    }

    private static KeyNumbersByAge parseNumbers(String inputString) {
        String[] tokens = inputString.split(";");
        return new KeyNumbersByAge(tokens[0],tokens[1], tokens[2], tokens[3], tokens[4],tokens[5], tokens[6]);
    }
}
