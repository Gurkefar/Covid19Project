package Data.Communally;

import java.util.ArrayList;

public class ListDeathCount {
    private ArrayList<DeathCount> deathCountList = new ArrayList<>();

    public ListDeathCount(ArrayList<DeathCount> deathCountList) {
        this.deathCountList = deathCountList;
    }

    public ArrayList<DeathCount> getDeathCountList() {
        return (ArrayList<DeathCount>) deathCountList.clone();
    }

}
