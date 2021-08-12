package Data.Communally;

public class KeyNumbers {
    private String date;
    private String testAmount;
    private String testedTotal;
    private String confirmedCases;
    private String covidNoMore;
    private String deathCount;
    private String newConfirmedCases;
    private String deathByDay;
    private String covidNoMoreByDay;
    private String hospitalized;
    private String respirator;
    private String intensiveCare;
    private String hospitalizedByDay;
    private String changeInHospitalization;
    private String changeInRespirator;
    private String changeInIntensiveCare;
    private String testedFirstTime;
    private String testsByDay;
    private String daily_Infected_Diff;
    private String test_totalPCR;
    private String test_totalAG;
    private String taily_Antigen_tests;
    private String daily_PCR_tests;
    private String daily_antigentest_diff;

    public KeyNumbers(String date, String testAmount, String testedTotal, String confirmedCases, String covidNoMore, String deathCount, String newConfirmedCases, String deathByDay, String covidNoMoreByDay, String hospitalized, String respirator, String intensiveCare, String hospitalizedByDay, String changeInHospitalization, String changeInRespirator, String changeInIntensiveCare, String testedFirstTime, String testsByDay, String daily_Infected_Diff, String test_totalPCR, String test_totalAG, String taily_Antigen_tests, String daily_PCR_tests, String daily_antigentest_diff) {
        this.date = date;
        this.testAmount = testAmount;
        this.testedTotal = testedTotal;
        this.confirmedCases = confirmedCases;
        this.covidNoMore = covidNoMore;
        this.deathCount = deathCount;
        this.newConfirmedCases = newConfirmedCases;
        this.deathByDay = deathByDay;
        this.covidNoMoreByDay = covidNoMoreByDay;
        this.hospitalized = hospitalized;
        this.respirator = respirator;
        this.intensiveCare = intensiveCare;
        this.hospitalizedByDay = hospitalizedByDay;
        this.changeInHospitalization = changeInHospitalization;
        this.changeInRespirator = changeInRespirator;
        this.changeInIntensiveCare = changeInIntensiveCare;
        this.testedFirstTime = testedFirstTime;
        this.testsByDay = testsByDay;
        this.daily_Infected_Diff = daily_Infected_Diff;
        this.test_totalPCR = test_totalPCR;
        this.test_totalAG = test_totalAG;
        this.taily_Antigen_tests = taily_Antigen_tests;
        this.daily_PCR_tests = daily_PCR_tests;
        this.daily_antigentest_diff = daily_antigentest_diff;
    }

    public String getDate() {
        return date;
    }

    public String getTestAmount() {
        return testAmount;
    }

    public String getTestedTotal() {
        return testedTotal;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public String getCovidNoMore() {
        return covidNoMore;
    }

    public String getDeathCount() {
        return deathCount;
    }

    public String getNewConfirmedCases() {
        return newConfirmedCases;
    }

    public String getDeathByDay() {
        return deathByDay;
    }

    public String getCovidNoMoreByDay() {
        return covidNoMoreByDay;
    }

    public String getHospitalized() {
        return hospitalized;
    }

    public String getRespirator() {
        return respirator;
    }

    public String getIntensiveCare() {
        return intensiveCare;
    }

    public String getHospitalizedByDay() {
        return hospitalizedByDay;
    }

    public String getChangeInHospitalization() {
        return changeInHospitalization;
    }

    public String getChangeInRespirator() {
        return changeInRespirator;
    }

    public String getChangeInIntensiveCare() {
        return changeInIntensiveCare;
    }

    public String getTestedFirstTime() {
        return testedFirstTime;
    }

    public String getTestsByDay() {
        return testsByDay;
    }

    public String getDaily_Infected_Diff() {
        return daily_Infected_Diff;
    }

    public String getTest_totalPCR() {
        return test_totalPCR;
    }

    public String getTest_totalAG() {
        return test_totalAG;
    }

    public String getTaily_Antigen_tests() {
        return taily_Antigen_tests;
    }

    public String getDaily_PCR_tests() {
        return daily_PCR_tests;
    }

    public String getDaily_antigentest_diff() {
        return daily_antigentest_diff;
    }

    @Override
    public String toString() {
        return "KeyNumbers{" +
                "date='" + date + '\'' +
                ", testAmount='" + testAmount + '\'' +
                ", testedTotal='" + testedTotal + '\'' +
                ", confirmedCases='" + confirmedCases + '\'' +
                ", covidNoMore='" + covidNoMore + '\'' +
                ", deathCount='" + deathCount + '\'' +
                ", newConfirmedCases='" + newConfirmedCases + '\'' +
                ", deathByDay='" + deathByDay + '\'' +
                ", covidNoMoreByDay='" + covidNoMoreByDay + '\'' +
                ", hospitalized='" + hospitalized + '\'' +
                ", respirator='" + respirator + '\'' +
                ", intensiveCare='" + intensiveCare + '\'' +
                ", hospitalizedByDay='" + hospitalizedByDay + '\'' +
                ", changeInHospitalization='" + changeInHospitalization + '\'' +
                ", changeInRespirator='" + changeInRespirator + '\'' +
                ", changeInIntensiveCare='" + changeInIntensiveCare + '\'' +
                ", testedFirstTime='" + testedFirstTime + '\'' +
                ", testsByDay='" + testsByDay + '\'' +
                ", daily_Infected_Diff='" + daily_Infected_Diff + '\'' +
                ", test_totalPCR='" + test_totalPCR + '\'' +
                ", test_totalAG='" + test_totalAG + '\'' +
                ", taily_Antigen_tests='" + taily_Antigen_tests + '\'' +
                ", daily_PCR_tests='" + daily_PCR_tests + '\'' +
                ", daily_antigentest_diff='" + daily_antigentest_diff + '\'' +
                '}';
    }
}
