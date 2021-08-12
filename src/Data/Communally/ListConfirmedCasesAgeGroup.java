package Data.Communally;

import java.util.ArrayList;

public class ListConfirmedCasesAgeGroup {
    private ArrayList<ConfirmedCasesAgeGroup> confirmedCasesAgeGroupArrayList = new ArrayList<>();

    public ListConfirmedCasesAgeGroup(ArrayList<ConfirmedCasesAgeGroup> confirmedCasesAgeGroupArrayList) {
        this.confirmedCasesAgeGroupArrayList = confirmedCasesAgeGroupArrayList;
    }

    public ArrayList<ConfirmedCasesAgeGroup> getConfirmedCasesListAgeGroup() {
        return (ArrayList<ConfirmedCasesAgeGroup>) confirmedCasesAgeGroupArrayList.clone();
    }
}
