package Data.Communally;

import java.util.ArrayList;

public class ListHospitalized {
    private ArrayList<Hospitalized> ageGroups = new ArrayList<>();

    public ListHospitalized(ArrayList<Hospitalized> ageGroups) {
        this.ageGroups = ageGroups;
    }


    public ArrayList<Hospitalized> getAgeGroups() {
        return (ArrayList<Hospitalized>) ageGroups.clone();
    }

}
