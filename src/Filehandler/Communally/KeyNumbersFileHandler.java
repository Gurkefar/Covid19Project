package Filehandler.Communally;

import Data.Communally.KeyNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class KeyNumbersFileHandler {
    public static ArrayList<KeyNumbers> getKeyNumbersFromFile(String filename) {
        ArrayList<KeyNumbers> numbers = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                KeyNumbers keyNumbers = parseNumbers(line);
                numbers.add(keyNumbers);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return numbers;
    }

    private static KeyNumbers parseNumbers(String inputString) {
        String[] tokens = inputString.split(";");
        return new KeyNumbers(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10],tokens[11],tokens[12],tokens[13],tokens[14],tokens[15],tokens[16],tokens[17],tokens[18],tokens[19],tokens[20],tokens[21],tokens[22],tokens[23]);
    }
}


