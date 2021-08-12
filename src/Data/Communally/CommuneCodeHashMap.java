package Data.Communally;

import java.util.HashMap;
import java.util.Map;

public class CommuneCodeHashMap {
    private HashMap<String, String> kpiValues = new HashMap<>();

    public CommuneCodeHashMap(HashMap<String, String> kpiValues) {
        for(Map.Entry<String, String> entry : kpiValues.entrySet()) {
            // {Key: value}
            this.kpiValues.put(entry.getKey(), entry.getValue());
        }
    }

    public String get(String key) {
        return kpiValues.get(key);
    }

    public HashMap<String, String> getKpiValues() {
        return kpiValues;
    }
}
