package Data.Regional;

import Data.Regional.ConfirmedCasesPrDay;

import java.util.ArrayList;

public class ListConfirmedCasesPrDay {
    private ArrayList<ConfirmedCasesPrDay> confirmedCasesPrDayList = new ArrayList<>();

    public ListConfirmedCasesPrDay(ArrayList<ConfirmedCasesPrDay> confirmedCasesPrDayList) {
        this.confirmedCasesPrDayList = confirmedCasesPrDayList;
    }

    public ArrayList<ConfirmedCasesPrDay> getConfirmedCasesPrDayList() {
        return (ArrayList<ConfirmedCasesPrDay>) confirmedCasesPrDayList.clone();
    }
}