package Data.Communally;

public class ConfirmedCases {
    private String commune;
    private String date;
    private String confirmedCases;

    public ConfirmedCases(String commune, String date, String confirmedCases) {
        this.commune = commune;
        this.date = date;
        this.confirmedCases = confirmedCases;
    }

    public String getCommune() {
        return commune;
    }

    public String getDate() {
        return date;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }


}
