
package Data.Regional;

public class ConfirmedCasesPrDay {
    private String region;
    private String date;
    private String confirmedCases;

    public ConfirmedCasesPrDay(String region, String date, String confirmedCases) {
        this.region = region;
        this.date = date;
        this.confirmedCases = confirmedCases;
    }

    public String getRegion() {
        return region;
    }

    public String getDate() {
        return date;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }
}