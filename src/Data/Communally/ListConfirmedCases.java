package Data.Communally;

import java.util.ArrayList;

public class ListConfirmedCases{
    private ArrayList<ConfirmedCases> confirmedCaseslList = new ArrayList<>();

    public ListConfirmedCases(ArrayList<ConfirmedCases> confirmedCaseslList) {
        this.confirmedCaseslList = confirmedCaseslList;
    }

    public ArrayList<ConfirmedCases> clone(){
            return (ArrayList<ConfirmedCases>) confirmedCaseslList.clone();
    }

    public ArrayList<ConfirmedCases> getConfirmedCaseslList() {
        return confirmedCaseslList;
    }
}
