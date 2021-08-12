package Data.Regional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class TmpAgeGroupDistribution {
    private final String[] ageGroups = new String[10];
    private int hospitalizedTotal = 0;

    public String[] getAgeGroupDistribution(String ageGroup, String hospitalized){
        switch (ageGroup) {
            case "0-9":
                this.ageGroups[0] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "10-19":
                this.ageGroups[1] = hospitalized;
                this. hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "20-29":
                this.ageGroups[2] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "30-39":
                this.ageGroups[3] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "40-49":
                this.ageGroups[4] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "50-59":
                this.ageGroups[5] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "60-69":
                this.ageGroups[6] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "70-79":
                this.ageGroups[7] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            case "80-89":
                this.ageGroups[8] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
            default:
                this.ageGroups[9] = hospitalized;
                this.hospitalizedTotal += Integer.parseInt(hospitalized);
                break;
        }
        System.out.println(this.hospitalizedTotal);
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
                new PieChart.Data("0 - 9: " + Integer.parseInt(ageGroups[0]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[0])),
                new PieChart.Data("10 - 19: " + Integer.parseInt(ageGroups[1]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[1])),
                new PieChart.Data("20 - 29: " + Integer.parseInt(ageGroups[2]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[2])),
                new PieChart.Data("30 - 39: " + Integer.parseInt(ageGroups[3]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[3])),
                new PieChart.Data("40 - 49: " + Integer.parseInt(ageGroups[4]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[4])),
                new PieChart.Data("50 - 59: " + Integer.parseInt(ageGroups[5]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[5])),
                new PieChart.Data("60 - 69: " + Integer.parseInt(ageGroups[6]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[6])),
                new PieChart.Data("70 - 79: " + Integer.parseInt(ageGroups[7]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[7])),
                new PieChart.Data("80 - 89: " + Integer.parseInt(ageGroups[8]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[8])),
                new PieChart.Data("90+: " + Integer.parseInt(ageGroups[9]) * 100 / hospitalizedTotal + "%", Integer.parseInt(ageGroups[9])));
    }

    public void sethospitalizedTotal(int hospitalizedTotal){
        this.hospitalizedTotal = hospitalizedTotal;
    }
}
