package Data.Regional;

import java.util.ArrayList;

public class ListKeyNumbers {
    private ArrayList<KeyNumbers> keyNumbersList = new ArrayList<>();

    public ListKeyNumbers(ArrayList<KeyNumbers> keyNumbersList) {
        this.keyNumbersList = keyNumbersList;
    }

    public ArrayList<KeyNumbers> getKeyNumbersList() {
        return (ArrayList<KeyNumbers>) keyNumbersList.clone();
    }
}
