
package Data.Regional;

import java.util.ArrayList;

public class ListDeadPrDay {
    private ArrayList<DeadPrDay> deadPrDayList = new ArrayList<>();

    public ListDeadPrDay(ArrayList<DeadPrDay> deadPrDayList) {
        this.deadPrDayList = deadPrDayList;
    }

    public ArrayList<DeadPrDay> getDeadPrDayList() {
        return (ArrayList<DeadPrDay>) deadPrDayList.clone();
    }
}