package Data.Communally;

public class Hospitalized {
    private String ageGroup;
    private String hospitalized;

    public Hospitalized(String ageGroup, String hospitalized) {
        this.ageGroup = ageGroup;
        this.hospitalized = hospitalized;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getHospitalized() {
        return hospitalized;
    }

    @Override
    public String toString() {
        return "Hospitalized{" +
                "ageGroup='" + ageGroup + '\'' +
                ", hospitalized='" + hospitalized + '\'' +
                '}';
    }
}
