package Data.Regional;

public class KeyNumbersByAge {
    private String region;
    private String ageGroup;
    private String confirmedCases;
    private String death;
    private String hospitalizedIntensive;
    private String hospitalized;
    private String date;

    public KeyNumbersByAge(String region, String ageGroup, String confirmedCases, String death, String hospitalizedIntensive, String hospitalized, String date) {
        this.region = region;
        this.ageGroup = ageGroup;
        this.confirmedCases = confirmedCases;
        this.death = death;
        this.hospitalizedIntensive = hospitalizedIntensive;
        this.hospitalized = hospitalized;
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public String getDeath() {
        return death;
    }

    public String getHospitalizedIntensive() {
        return hospitalizedIntensive;
    }

    public String getHospitalized() {
        return hospitalized;
    }

    public String getDate() {
        return date;
    }
}
