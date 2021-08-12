package Data.Regional;

public class DeadPrDay {
    private String region;
    private String date;
    private String dead;
    private String totalDeaths;

    public DeadPrDay(String region, String date, String dead, String totalDeaths) {
        this.region = region;
        this.date = date;
        this.dead = dead;
        this.totalDeaths = totalDeaths;
    }

    public String getRegion() {
        return region;
    }

    public String getDate() {
        return date;
    }

    public String getDead() {
        return dead;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }
}