package Data.Communally;

public class Map {
    private String commune;
    private String confirmedCases;
    private String tests7Days;
    private String positivePercent;

    public Map(String commune, String confirmedCases, String tests7Days, String positivePercent) {
        this.commune = commune;
        this.confirmedCases = confirmedCases;
        this.tests7Days = tests7Days;
        this.positivePercent = positivePercent;
    }

    public String getCommune() {
        return commune;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public String getTests7Days() {
        return tests7Days;
    }

    public String getPositivePercent() {
        return positivePercent;
    }

    @Override
    public String toString() {
        return "Map{" +
                "commune='" + commune + '\'' +
                ", confirmedCases='" + confirmedCases + '\'' +
                ", tests7Days='" + tests7Days + '\'' +
                ", positivePercent='" + positivePercent + '\'' +
                '}';
    }
}
