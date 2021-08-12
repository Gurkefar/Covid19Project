package sample;

import Data.Communally.Hospitalized;
import Data.Communally.ListHospitalized;
import Filehandler.Communally.HospitalizedFileHandler;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class TestDemo {

    @Test
    public void testReadfromCSV(){
        HospitalizedFileHandler hospitalizedFileHandler = new HospitalizedFileHandler();
        ArrayList<Hospitalized> test = HospitalizedFileHandler.getAgeGroupFromFile("resources/Covid19Stats/Kommunalt_DB/03_indlæggelser_pr_aldersgrp.csv");
        ListHospitalized listHospitalized = new ListHospitalized(test);
        ArrayList<Hospitalized> hospitalized = listHospitalized.getAgeGroups();
        String actualResult = hospitalized.get(2).getHospitalized();
        assertEquals("608",actualResult);
    }
}