package Data.Communally;

import java.util.ArrayList;

public class ListKeyNumbers {
    private String phaseNumber;
    private ArrayList<KeyNumbers> keyNumbers = new ArrayList<>();

    public ListKeyNumbers(String phaseNumber, ArrayList<KeyNumbers> keyNumbers) {
        this.phaseNumber = phaseNumber;
        this.keyNumbers = (ArrayList<KeyNumbers>) keyNumbers.clone();
    }

    public void printKeyNumbers() {
        for (KeyNumbers keyNumbers : keyNumbers) {
            System.out.println(keyNumbers);
        }
    }

    public ArrayList<KeyNumbers> getKeyNumbers() {
        return (ArrayList<KeyNumbers>) keyNumbers.clone();
    }


}


