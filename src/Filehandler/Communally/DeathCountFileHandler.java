package Filehandler.Communally;

import Data.Communally.DeathCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DeathCountFileHandler {
    public static ArrayList<DeathCount> getDeathCountFromFile(String filename) {
        ArrayList<DeathCount> deathCountList = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                DeathCount deathCount = parseNumbers(line);
                deathCountList.add(deathCount);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return deathCountList;
    }

    private static DeathCount parseNumbers(String inputString) {
        String[] tokens = inputString.split(";");
        return new DeathCount(tokens[0],tokens[1], tokens[2]);
    }
}
