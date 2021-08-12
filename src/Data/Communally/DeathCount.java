package Data.Communally;

public class DeathCount {
    private String ageGroup;
    private String confirmedCases;
    private String  deathCount;

    public DeathCount(String ageGroup, String confirmedCases, String deathCount) {
        this.ageGroup = ageGroup;
        this.confirmedCases = confirmedCases;
        this.deathCount = deathCount;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public String getDeathCount() {
        return deathCount;
    }

    @Override
    public String toString() {
        return "DeathCount{" +
                "ageGroup='" + ageGroup + '\'' +
                ", confirmedCases='" + confirmedCases + '\'' +
                ", deathCount='" + deathCount + '\'' +
                '}';
    }
}
