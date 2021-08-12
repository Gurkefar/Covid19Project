package Data.Communally;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class TmpAgeGroupDistribution {
    private String[] ageGroups = new String[10];
    private int cases = 0;

    public String[] getAgeGroupDistribution(String ageGroup, String confirmedCases){
        switch (ageGroup) {
            case "0-9":
                this.ageGroups[0] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            case "10-19":
                this.ageGroups[1] = confirmedCases;
                this. cases += Integer.parseInt(confirmedCases);
                break;
            case "20-29":
                this.ageGroups[2] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            case "30-39":
                this.ageGroups[3] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            case "40-49":
                this.ageGroups[4] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            case "50-59":
                this.ageGroups[5] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            case "60-69":
                this.ageGroups[6] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            case "70-79":
                this.ageGroups[7] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            case "80-89":
                this.ageGroups[8] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
            default:
                this.ageGroups[9] = confirmedCases;
                this.cases += Integer.parseInt(confirmedCases);
                break;
        }
        return this.ageGroups;
    }

    public void setNullPointers(){
        for (int i = 0; i < 10; i++){
            if (ageGroups[i] == null){
                ageGroups[i] = "0";
            }
        }
    }

    public ObservableList<PieChart.Data> getPieChartData(){
        return FXCollections.observableArrayList(
                new PieChart.Data("0 - 9: " + Integer.parseInt(ageGroups[0]) * 100 / cases + "%", Integer.parseInt(ageGroups[0])),
                new PieChart.Data("10 - 19: " + Integer.parseInt(ageGroups[1]) * 100 / cases + "%", Integer.parseInt(ageGroups[1])),
                new PieChart.Data("20 - 29: " + Integer.parseInt(ageGroups[2]) * 100 / cases + "%", Integer.parseInt(ageGroups[2])),
                new PieChart.Data("30 - 39: " + Integer.parseInt(ageGroups[3]) * 100 / cases + "%", Integer.parseInt(ageGroups[3])),
                new PieChart.Data("40 - 49: " + Integer.parseInt(ageGroups[4]) * 100 / cases + "%", Integer.parseInt(ageGroups[4])),
                new PieChart.Data("50 - 59: " + Integer.parseInt(ageGroups[5]) * 100 / cases + "%", Integer.parseInt(ageGroups[5])),
                new PieChart.Data("60 - 69: " + Integer.parseInt(ageGroups[6]) * 100 / cases + "%", Integer.parseInt(ageGroups[6])),
                new PieChart.Data("70 - 79: " + Integer.parseInt(ageGroups[7]) * 100 / cases + "%", Integer.parseInt(ageGroups[7])),
                new PieChart.Data("80 - 89: " + Integer.parseInt(ageGroups[8]) * 100 / cases + "%", Integer.parseInt(ageGroups[8])),
                new PieChart.Data("90+: " + Integer.parseInt(ageGroups[9]) * 100 / cases + "%", Integer.parseInt(ageGroups[9])));
    }

    public void setCases(int cases){
        this.cases = cases;
    }

    public int getCases(){
        return this.cases;
    }
}
