package Data.Regional;

public class KeyNumbers {
    private String date;
    private String region;
    private String gender;
    private String confirmedCases;
    private String deathCount;
    private String infectedNoMore;
    private String hospitalized;
    private String testedTotal;
    private String differenceConfirmedCases;
    private String differenceDeathCount;
    private String differenceInfectedNoMore;
    private String differenceHospitalized;
    private String differenceTestedTotal;
    private String testsTotal;
    private String differenceTestsTotal;
    private String test_AG;
    private String test_AG_diff;

    public KeyNumbers(String date, String region, String gender, String confirmedCases, String deathCount, String infectedNoMore, String hospitalized, String testedTotal, String differenceConfirmedCases, String differenceDeathCount, String differenceInfectedNoMore, String differenceHospitalized, String differenceTestedTotal, String testsTotal, String differenceTestsTotal, String test_AG, String test_AG_diff) {
        this.date = date;
        this.region = region;
        this.gender = gender;
        this.confirmedCases = confirmedCases;
        this.deathCount = deathCount;
        this.infectedNoMore = infectedNoMore;
        this.hospitalized = hospitalized;
        this.testedTotal = testedTotal;
        this.differenceConfirmedCases = differenceConfirmedCases;
        this.differenceDeathCount = differenceDeathCount;
        this.differenceInfectedNoMore = differenceInfectedNoMore;
        this.differenceHospitalized = differenceHospitalized;
        this.differenceTestedTotal = differenceTestedTotal;
        this.testsTotal = testsTotal;
        this.differenceTestsTotal = differenceTestsTotal;
        this.test_AG = test_AG;
        this.test_AG_diff = test_AG_diff;
    }

    public String getDate() {
        return date;
    }

    public String getRegion() {
        return region;
    }

    public String getGender() {
        return gender;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public String getDeathCount() {
        return deathCount;
    }

    public String getInfectedNoMore() {
        return infectedNoMore;
    }

    public String getHospitalized() {
        return hospitalized;
    }

    public String getTestedTotal() {
        return testedTotal;
    }

    public String getDifferenceConfirmedCases() {
        return differenceConfirmedCases;
    }

    public String getDifferenceDeathCount() {
        return differenceDeathCount;
    }

    public String getDifferenceInfectedNoMore() {
        return differenceInfectedNoMore;
    }

    public String getDifferenceHospitalized() {
        return differenceHospitalized;
    }

    public String getDifferenceTestedTotal() {
        return differenceTestedTotal;
    }

    public String getTestsTotal() {
        return testsTotal;
    }

    public String getDifferenceTestsTotal() {
        return differenceTestsTotal;
    }

    public String getTest_AG() {
        return test_AG;
    }

    public String getTest_AG_diff() {
        return test_AG_diff;
    }
}

