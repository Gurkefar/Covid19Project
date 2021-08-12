package Data.Communally;

import java.util.ArrayList;

public class ListMap {
    private ArrayList<Map> mapList = new ArrayList<>();

    public ListMap(ArrayList<Map> mapList) {
        this.mapList = mapList;
    }

    public ArrayList<Map> getMapList() {
        return (ArrayList<Map>) mapList.clone();
    }

}
