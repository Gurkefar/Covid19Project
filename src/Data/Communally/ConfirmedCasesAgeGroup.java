package Data.Communally;

public class ConfirmedCasesAgeGroup {
    private String commune;
    private String ageGroup;
    private String confirmedCases;
    private String date;

    public ConfirmedCasesAgeGroup(String commune, String ageGroup, String confirmedCases, String date) {
        this.commune = commune;
        this.ageGroup = ageGroup;
        this.confirmedCases = confirmedCases;
        this.date = date;
    }

    public String getCommune() {
        return commune;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public String getDate() {
        return date;
    }
}
