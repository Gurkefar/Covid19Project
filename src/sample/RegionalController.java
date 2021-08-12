package sample;

import Data.Regional.*;
import Filehandler.Regional.ConfirmedCasesPrDayFileHandler;
import Filehandler.Regional.DeadPrDayFileHandler;
import Filehandler.Regional.KeyNumbersByAgeFileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RegionalController implements Initializable {

    /* all ints below are used for determining keynumbers from file 07_antal_doede_pr_dag_pr_region.csv - which is
    based on gender, why we add, e.g. confirmed cases for male and female, and get the sum, which will be displayed
    for a certain region
     */
    private int confirmedCases = 0;
    private int deathCountTmp = 0;
    private int hospitalizedTmp = 0;
    private int testedTotalTmp = 0;
    private int differenceConfirmedCases = 0;
    private int infectedNoMore = 0;
    private int differenceHospitalizedTmp = 0;
    private int testsTotalTmp = 0;

    // object for creating piechart
    Data.Regional.TmpAgeGroupDistribution tmpAgeGroupDistribution = new Data.Regional.TmpAgeGroupDistribution();

    //objects, containing covid19 data
    ArrayList<ConfirmedCasesPrDay> file08 = ConfirmedCasesPrDayFileHandler.getConfirmedCasesPrDayFromFile("resources/Covid19Stats/Regionalt_DB/08_bekraeftede_tilfaelde_pr_dag_pr_regions.csv");
    ListConfirmedCasesPrDay listConfirmedCasesPrDay = new ListConfirmedCasesPrDay(file08);
    ArrayList<ConfirmedCasesPrDay> confirmedCasesPrDays = listConfirmedCasesPrDay.getConfirmedCasesPrDayList();

    ArrayList<DeadPrDay> file07 = DeadPrDayFileHandler.getDeadPrDayFromFile("resources/Covid19Stats/Regionalt_DB/07_antal_doede_pr_dag_pr_region.csv");
    ListDeadPrDay listDeadPrDay = new ListDeadPrDay(file07);
    ArrayList<DeadPrDay> deadPrDays = listDeadPrDay.getDeadPrDayList();

    ArrayList<Data.Regional.KeyNumbers> file01Regional = Filehandler.Regional.KeyNumbersFileHandler.getKeyNumbersFromFile("resources/Covid19Stats/Regionalt_DB/01_noegle_tal.csv");
    Data.Regional.ListKeyNumbers listKeyNumbersRegional = new Data.Regional.ListKeyNumbers(file01Regional);
    ArrayList<Data.Regional.KeyNumbers> keyNumbersRegional = listKeyNumbersRegional.getKeyNumbersList();

    ArrayList<KeyNumbersByAge> file11 = KeyNumbersByAgeFileHandler.getKeyNumbersByAgeFromFile("resources/Covid19Stats/Regionalt_DB/11_noegletal_pr_region_pr_aldersgruppe.csv");
    ListKeyNumbersByAge listKeyNumbersByAge = new ListKeyNumbersByAge(file11);
    ArrayList<KeyNumbersByAge> keyNumbersByAges = listKeyNumbersByAge.getKeyNumbersByAgeList();

    // keeping track of which region we are on
    private String region;

    //Key numbers corresponding to numbers in keyNumbersRegional
    @FXML
    Label regionLabel;
    @FXML
    private Label infected;
    @FXML
    private Label hospitalized;
    @FXML
    private Label testedTotal;
    @FXML
    private Label differenceInfected;
    @FXML
    private Label registeredNoCovid;
    @FXML
    private Label deathCount;
    @FXML
    private Label testsTotal;
    @FXML
    private Label differenceHospitalized;

    //linechart based on numbers in confirmedCasesPrDays and deadPrDays
    @FXML
    private LineChart<String, Integer> infectedDevelopment;

    //piechart baed on numbers in keyNumbersByAges
    @FXML
    private PieChart ageGroupDistribution;

    //"excessive numbers" in keyNumbersRegional
    @FXML
    private ListView extraInfoListView;

    //barchart based on keyNumbersByAges
    @FXML
    private BarChart<?, ?> ageGroupDeath;
    //choice box with the "daglige smittetal" or "døde" options  connected to infectedDevelopment
    @FXML
    private ChoiceBox<String> choiceBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.region = "Hovedstaden"; // initialize first region to "hovedstaden"
        regionLabel.setText("Hovedstaden"); // set regionLabel

        for (Data.Regional.KeyNumbers i : keyNumbersRegional){ // iterate over keyNumbersRegional
            if (this.region.equals(i.getRegion())){ // find objects, where we are at correct region
                confirmedCases += Integer.parseInt(i.getConfirmedCases()); // add for both female and male
                deathCountTmp += Integer.parseInt(i.getDeathCount());
                hospitalizedTmp += Integer.parseInt(i.getHospitalized());
                testedTotalTmp += Integer.parseInt(i.getTestedTotal());
                differenceConfirmedCases += Integer.parseInt(i.getDifferenceConfirmedCases());
                infectedNoMore += Integer.parseInt(i.getInfectedNoMore());
                differenceHospitalizedTmp += Integer.parseInt(i.getDifferenceHospitalized());
                testsTotalTmp += Integer.parseInt(i.getTestsTotal());
            }
        }
        // set the labels
        infected.setText(String.valueOf(confirmedCases));
        testedTotal.setText(String.valueOf(testedTotalTmp));
        hospitalized.setText(String.valueOf(hospitalizedTmp));
        differenceInfected.setText(String.valueOf(differenceConfirmedCases));
        registeredNoCovid.setText(String.valueOf(infectedNoMore));
        deathCount.setText(String.valueOf(deathCountTmp));
        testsTotal.setText(String.valueOf(testsTotalTmp));
        differenceHospitalized.setText(String.valueOf(differenceHospitalizedTmp));


        XYChart.Series seriesInfected = new XYChart.Series(); // graph, infected over time regional

        for (int i = 0; i < confirmedCasesPrDays.size(); i++){
            if (confirmedCasesPrDays.get(i).getRegion().equals("Hovedstaden")){ // create data for linechart
                seriesInfected.getData().add(new XYChart.Data(confirmedCasesPrDays.get(i).getDate(), Integer.valueOf(confirmedCasesPrDays.get(i).getConfirmedCases())));
            }
        }

        infectedDevelopment.getData().addAll(seriesInfected); // add data to linechart
        infectedDevelopment.setCreateSymbols(false); // remove dots on graph

        for (KeyNumbersByAge j : keyNumbersByAges){
            if (j.getRegion().equals("Hovedstaden")){ // piechart of a certain region
                // calling method in tmpAgeGroupDistribution, which sets a certain ageGroupDistribution
                tmpAgeGroupDistribution.getAgeGroupDistribution(j.getAgeGroup(), j.getHospitalized());
            }
        }

        tmpAgeGroupDistribution.setNullPointers(); // for handling null cases
        //create and set piechart data
        ObservableList<PieChart.Data> pieChartData = tmpAgeGroupDistribution.getPieChartData();
        ageGroupDistribution.setData(pieChartData);
        //reset hospitalized in tmpAgeGroupDistribution
        tmpAgeGroupDistribution.sethospitalizedTotal(0);

        //initialize choice box
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Døde", "Daglige smittetal");
        choiceBox.setItems(availableChoices);
        //initialie choicebox for graph to infected over time
        choiceBox.setValue("Daglige smittetal");

        XYChart.Series series2 = new XYChart.Series(); // bar chart - death distribution in different age groups
        for (int i = 0; i < keyNumbersByAges.size(); i++) {
            if (keyNumbersByAges.get(i).getRegion().equals("Hovedstaden")) { // create barchart data
                series2.getData().add(new XYChart.Data(keyNumbersByAges.get(i).getAgeGroup(), Integer.valueOf(keyNumbersByAges.get(i).getDeath())));
            }
        }
        ageGroupDeath.getData().addAll(series2); // create barchart
    }

    @FXML
    void getRegionalKeyNumbers(ActionEvent event) { // set labels in top of GUI (nøgletal)
        MenuItem menuItem = (MenuItem) event.getSource(); // get requested region

        this.region = menuItem.getText();
        regionLabel.setText(this.region);

        for (Data.Regional.KeyNumbers i : keyNumbersRegional){ // iterate over keyNumbersRegional
            if (this.region.equals(i.getRegion())){ // find objects, where we are at correct region
                confirmedCases += Integer.parseInt(i.getConfirmedCases()); // add for both female and male
                deathCountTmp += Integer.parseInt(i.getDeathCount());
                hospitalizedTmp += Integer.parseInt(i.getHospitalized());
                testedTotalTmp += Integer.parseInt(i.getTestedTotal());
                differenceConfirmedCases += Integer.parseInt(i.getDifferenceConfirmedCases());
                infectedNoMore += Integer.parseInt(i.getInfectedNoMore());
                differenceHospitalizedTmp += Integer.parseInt(i.getDifferenceHospitalized());
                testsTotalTmp += Integer.parseInt(i.getTestsTotal());
            }
        } //set labels
        infected.setText(String.valueOf(confirmedCases));
        testedTotal.setText(String.valueOf(testedTotalTmp));
        hospitalized.setText(String.valueOf(hospitalizedTmp));
        differenceInfected.setText(String.valueOf(differenceConfirmedCases));
        registeredNoCovid.setText(String.valueOf(infectedNoMore));
        deathCount.setText(String.valueOf(deathCountTmp));
        testsTotal.setText(String.valueOf(testsTotalTmp));
        differenceHospitalized.setText(String.valueOf(differenceHospitalizedTmp));

        // Update piechart for correct regional data - same procedure as in initialize
        for (KeyNumbersByAge j : keyNumbersByAges){
            if (j.getRegion().equals(this.region)){
                tmpAgeGroupDistribution.getAgeGroupDistribution(j.getAgeGroup(), j.getHospitalized());
            }
        }
        tmpAgeGroupDistribution.setNullPointers();
        ObservableList<PieChart.Data> pieChartData = tmpAgeGroupDistribution.getPieChartData();
        ageGroupDistribution.setData(pieChartData);
        tmpAgeGroupDistribution.sethospitalizedTotal(0);

        // Update graph for correct regional data - first clear the data
        infectedDevelopment.getData().clear();
        infectedDevelopment.getData().removeAll();
        XYChart.Series seriesInfected = new XYChart.Series(); // graph, infected over time regional
        XYChart.Series seriesDeathByDay= new XYChart.Series(); // graph, deaths every day over time regional
        // Check what choice for graph is selected
        String choice = choiceBox.getSelectionModel().getSelectedItem();

        //based on choice, create graph
        if (choice.equals("Daglige smittetal")){
            for (int i = 0; i < confirmedCasesPrDays.size(); i++){
                if (confirmedCasesPrDays.get(i).getRegion().equals(this.region)){
                    seriesInfected.getData().add(new XYChart.Data(confirmedCasesPrDays.get(i).getDate(), Integer.valueOf(confirmedCasesPrDays.get(i).getConfirmedCases())));
                }
            }
            infectedDevelopment.getData().addAll(seriesInfected);
        }
        else if (choice.equals("Døde")){
            for (int i = 0; i < deadPrDays.size(); i++){
                if (deadPrDays.get(i).getRegion().equals(this.region)){
                    seriesDeathByDay.getData().add(new XYChart.Data(deadPrDays.get(i).getDate(), Integer.valueOf(deadPrDays.get(i).getDead())));
                }
            }
            infectedDevelopment.getData().addAll(seriesDeathByDay);
        }

        //update barchart, first clear it, then get numbers for correct region
        ageGroupDeath.getData().clear();
        XYChart.Series series2 = new XYChart.Series(); // bar chart - death distribution in different age groups
        for (int i = 0; i < keyNumbersByAges.size(); i++) {
            if (keyNumbersByAges.get(i).getRegion().equals(this.region)) {
                if (keyNumbersByAges.get(i).getDeath() != null) {
                    series2.getData().add(new XYChart.Data(keyNumbersByAges.get(i).getAgeGroup(), Integer.valueOf(keyNumbersByAges.get(i).getDeath())));
                }
            }
        }
        ageGroupDeath.getData().addAll(series2);
    }

    @FXML
    void setGraph(ActionEvent event)  { // button, which updates graph, if you are on same region, but want to view different numbers
        infectedDevelopment.getData().clear();
        infectedDevelopment.getData().removeAll();
        XYChart.Series seriesInfected = new XYChart.Series(); // graph, infected over time regional
        XYChart.Series seriesDeathByDay= new XYChart.Series(); // graph, deaths every day over time regional

        String choice = choiceBox.getSelectionModel().getSelectedItem();

        //check for value in choice
        if (choice.equals("Daglige smittetal")){
            for (int i = 0; i < confirmedCasesPrDays.size(); i++){
                if (confirmedCasesPrDays.get(i).getRegion().equals(this.region)){
                    seriesInfected.getData().add(new XYChart.Data(confirmedCasesPrDays.get(i).getDate(), Integer.valueOf(confirmedCasesPrDays.get(i).getConfirmedCases())));
                }
            }
            infectedDevelopment.getData().addAll(seriesInfected);
        }
        else{
            for (int i = 0; i < deadPrDays.size(); i++){
                if (deadPrDays.get(i).getRegion().equals(this.region)){
                    seriesDeathByDay.getData().add(new XYChart.Data(deadPrDays.get(i).getDate(), Integer.valueOf(deadPrDays.get(i).getDead())));
                }
            }
            infectedDevelopment.getData().addAll(seriesDeathByDay);
        }
    }

    // set extra information in labels on request
    public void setExtraInformation(ActionEvent actionEvent) {
        extraInfoListView.getItems().clear();
        int testsAG = 0;
        int diffTestsAG = 0;
        int diffDeath = 0;
        int diffInfectedNoMore = 0;
        int diffTestedTotal = 0;


        for (Data.Regional.KeyNumbers i : keyNumbersRegional){
            if (this.region.equals(i.getRegion())){
                testsAG += Integer.parseInt(i.getTest_AG());
                diffTestsAG += Integer.parseInt(i.getTest_AG_diff());
                diffDeath += Integer.parseInt(i.getDifferenceDeathCount());
                diffInfectedNoMore += Integer.parseInt(i.getDifferenceInfectedNoMore());
                diffTestedTotal += Integer.parseInt(i.getDifferenceTestedTotal());
            }
        }
        extraInfoListView.getItems().addAll("Antigen tests brugt ialt: " + testsAG);
        extraInfoListView.getItems().addAll("Forskel i antigen tests: " + diffTestsAG);
        extraInfoListView.getItems().addAll("Forskel i dødstal: " + diffDeath);
        extraInfoListView.getItems().addAll("Forskel i antal registreret raske: " + diffInfectedNoMore);
        extraInfoListView.getItems().addAll("Forskel i antal testede: "+ diffTestedTotal);


    }

    /* changeSceneToRegions and changeSceneToCommunes are connected to buttons which switches scenes
       created in two different fxml files and controllers.
     */
    @FXML
    void changeSceneToRegions(ActionEvent event) throws IOException {
        Parent regionsParent = FXMLLoader.load(getClass().getResource("Regions.fxml"));
        Scene regionsScene = new Scene(regionsParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(regionsScene);
        window.show();
    }
    @FXML
    void changeSceneToCommunes(ActionEvent event) throws IOException {
        Parent communesParent = FXMLLoader.load(getClass().getResource("Communes.fxml"));
        Scene communesScene = new Scene(communesParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(communesScene);
        window.show();
    }
}
