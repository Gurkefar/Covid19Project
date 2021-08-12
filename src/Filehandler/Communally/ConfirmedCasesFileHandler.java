package Filehandler.Communally;

import Data.Communally.CommuneCodeHashMap;
import Data.Communally.ConfirmedCases;
import Data.Communally.ListConfirmedCases;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ConfirmedCasesFileHandler {
    private static final CommuneCodeHashMap communeCodeHashMap = new CommuneCodeHashMap(CommuneCodeFileHandler.getCommuneCodesFromFile("resources/Commune_codes.csv"));

    public static ArrayList<ConfirmedCases> getConfirmedCasesFromFile(String filename) {
        ArrayList<ConfirmedCases> confirmedCasesList = new ArrayList<>();

        Path file = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                ConfirmedCases confirmedCases = parseNumbers(line);
                confirmedCasesList.add(confirmedCases);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return confirmedCasesList;
    }

    private static ConfirmedCases parseNumbers(String inputString) {
        //CommuneCodeHashMap communeCodeHashMap = new CommuneCodeHashMap(CommuneCodeFileHandler.getCommuneCodesFromFile("resources/Commune_codes.csv"));
        String[] tokens = inputString.split(";");
        return new ConfirmedCases(communeCodeHashMap.get(tokens[0]),tokens[1], tokens[2]);
    }
}




