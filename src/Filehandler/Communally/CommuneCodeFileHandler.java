package Filehandler.Communally;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class CommuneCodeFileHandler {
    public static HashMap<String, String> getCommuneCodesFromFile(String filename) {
        HashMap<String, String> communeCodeHashMap = new HashMap<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String code = parts[0].trim();
                String commune = parts[1].trim();
                communeCodeHashMap.put(code,commune);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return communeCodeHashMap;
    }
}