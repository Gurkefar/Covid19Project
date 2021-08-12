package Data.Regional;

import java.util.ArrayList;

public class ListKeyNumbersByAge {
    private ArrayList<KeyNumbersByAge> keyNumbersByAgeList = new ArrayList<>();

    public ListKeyNumbersByAge(ArrayList<KeyNumbersByAge> keyNumbersByAgeList) {
        this.keyNumbersByAgeList = keyNumbersByAgeList;
    }

    public ArrayList<KeyNumbersByAge> getKeyNumbersByAgeList() {
        return (ArrayList<KeyNumbersByAge>) keyNumbersByAgeList.clone();
    }
}
