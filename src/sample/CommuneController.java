package sample;

import Data.Communally.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import Data.Communally.*;
import Filehandler.Communally.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CommuneController implements Initializable {
    // object for creating piechart
    TmpAgeGroupDistribution tmpAgeGroupDistribution = new TmpAgeGroupDistribution();

    //objects, containing covid19 data
    ArrayList<KeyNumbers> file01 = KeyNumbersFileHandler.getKeyNumbersFromFile("resources/Covid19Stats/Kommunalt_DB/01_noegletal.csv");
    ListKeyNumbers listKeyNumbers = new ListKeyNumbers("1", file01);
    ArrayList<KeyNumbers> keyNumbers = listKeyNumbers.getKeyNumbers();

    ArrayList<ConfirmedCasesAgeGroup> file09 = ConfirmedCasesAgeGroupFileHandler.getConfirmedCasesAgeGroupFromFile("resources/Covid19Stats/Kommunalt_DB/09_tilfaelde_aldersgrp_kommuner.csv");
    ListConfirmedCasesAgeGroup listConfirmedCasesAgeGroup = new ListConfirmedCasesAgeGroup(file09);
    ArrayList<ConfirmedCasesAgeGroup> confirmedCasesAgeGroups = listConfirmedCasesAgeGroup.getConfirmedCasesListAgeGroup();

    CommuneCodeHashMap communeCodeHashMap = new CommuneCodeHashMap(CommuneCodeFileHandler.getCommuneCodesFromFile("resources/Commune_codes.csv"));
    HashMap<String, String> communeCodes = communeCodeHashMap.getKpiValues();

    ArrayList<DeathCount> file04 = DeathCountFileHandler.getDeathCountFromFile("resources/Covid19Stats/Kommunalt_DB/04_bekraeftede_tilfaelde_doed_pr_aldersgrp.csv");
    ListDeathCount listDeathCount = new ListDeathCount(file04);
    ArrayList<DeathCount> deathCounts = listDeathCount.getDeathCountList();

    ArrayList<Map> file10 = MapFileHandler.getMapFromFile("resources/Covid19Stats/Kommunalt_DB/10_kort_pr_kommune.csv");
    ListMap listMap = new ListMap(file10);
    ArrayList<Map> mapArrayList = listMap.getMapList();

    // keep track of date
    private String date;

    //Key numbers corresponding to numbers in keyNumbers
    @FXML
    Label infected;
    @FXML
    Label testedTotal;
    @FXML
    Label hospitalized;
    @FXML
    Label intensive;
    @FXML
    Label respirator;
    @FXML
    Label deathCount;
    @FXML
    Label deathByDay;
    @FXML
    Label infectedByDay;

    //linechart based on numbers in confirmedCasesPrDays and deadPrDays
    @FXML
    private LineChart<String, Integer> infectedDevelopment;

    //piechart baed on numbers in confirmedCasesAgeGroups
    @FXML
    private PieChart ageGroupDistribution;

    // listview made by communecodes
    @FXML
    private ListView communesListView;

    // listview based on numbers in keyNumbers
    @FXML
    private ListView extraInfoListView;

    // barchart based on numbers in deathCounts
    @FXML
    private BarChart<?, ?> ageGroupDeath;

    // listview based on numbers in mapArrayList
    @FXML
    private ListView positiveProcentListView;

    // 2 labels, to display current commune
    @FXML
    private Label communeLabel;
    @FXML
    private Label communeLabel1;

    // date picker (calender)
    @FXML
    private DatePicker datePicker;

    // textfield to search for commune
    @FXML
    private TextField textField;

    // if invalid commune entered, handle it
    @FXML
    private Label invalidInput;

    // choicebox for graph
    @FXML
    private ChoiceBox<String> choiceBox;

    // if data for a certain date doesen't exist, handle it
    @FXML
    private Label dateException;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.date = "2021-04-16"; // initialize newest date of data
        String commune = "København";
        communeLabel1.setText(commune); // initialize first commune as the capital
        datePicker.setValue(LocalDate.parse(this.date)); // assign value in datepicker to this.date

        boolean flag = false;
        int counter = 0; // for indexing in arraylist keyNumbers
        for (KeyNumbers i : keyNumbers) { // set all keynumbers for initial  date
            if (i.getDate().equals(date)) {
                infected.setText(keyNumbers.get(counter).getConfirmedCases());
                testedTotal.setText(keyNumbers.get(counter).getTestedTotal());
                hospitalized.setText(keyNumbers.get(counter).getHospitalized());
                intensive.setText(keyNumbers.get(counter).getIntensiveCare());
                respirator.setText(keyNumbers.get(counter).getRespirator());
                deathCount.setText(keyNumbers.get(counter).getDeathCount());
                deathByDay.setText(keyNumbers.get(counter).getDeathByDay());
                infectedByDay.setText(keyNumbers.get(counter).getNewConfirmedCases());
                flag = true;
            }
            counter++; // increment each iteration
        }

        for (ConfirmedCasesAgeGroup i : confirmedCasesAgeGroups) {
            if (commune.equals(communeCodeHashMap.get(i.getCommune()))) { // create data for pieChart for initial commune
                // calling method in tmpAgeGroupDistribution, which sets a certain ageGroupDistribution
                tmpAgeGroupDistribution.getAgeGroupDistribution(i.getAgeGroup(), i.getConfirmedCases());
                tmpAgeGroupDistribution.getCases();
            }
        }

        //create and set piechart data
        ObservableList<PieChart.Data> pieChartData = tmpAgeGroupDistribution.getPieChartData();
        ageGroupDistribution.setData(pieChartData);
        // reset number of cases
        tmpAgeGroupDistribution.setCases(0);
        //also set setPositiveProcentListView for commune
        setPositiveProcentListView(commune);

        XYChart.Series seriesInfected = new XYChart.Series(); // graph, infected over time globally
        for (int i = 300; i > 0; i--) { // iterate over 300 dates
            int index = keyNumbers.size() - i - 1;
            if (!(Integer.valueOf(keyNumbers.get(index).getNewConfirmedCases()) < 0)) { // avoid negative data
                // create data for graph
                seriesInfected.getData().add(new XYChart.Data(keyNumbers.get(index).getDate(), Integer.valueOf(keyNumbers.get(index).getNewConfirmedCases())));
            }
        }
        //add data to graph
        infectedDevelopment.getData().addAll(seriesInfected);
        infectedDevelopment.setCreateSymbols(false);

        // Initializing communesListView
        Iterator<String> i = communeCodes.keySet().iterator();
        for (int j = 0; j < communeCodes.size(); j++) {
            communesListView.getItems().addAll(communeCodes.get(i.next()));
        }

        XYChart.Series series2 = new XYChart.Series(); // bar chart - death distribution in different age groups
        for (int j = 0; j < deathCounts.size(); j++) {
            // create data for bar chart
            series2.getData().add(new XYChart.Data(deathCounts.get(j).getAgeGroup(), Integer.valueOf(deathCounts.get(j).getDeathCount())));
        }
        // add data to bar chart
        ageGroupDeath.getData().addAll(series2);

        //initialize available choices in choicebox for graph
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Døde", "Daglige smittetal");
        choiceBox.setItems(availableChoices);
    }
//hello

    @FXML
    void setGraph(ActionEvent event)  {
        XYChart.Series seriesInfected = new XYChart.Series(); // graph, infected over time globally
        XYChart.Series seriesDeathByDay= new XYChart.Series(); // graph, deaths every day over time globally
        infectedDevelopment.getData().clear();

        String choice = choiceBox.getSelectionModel().getSelectedItem();

        if (choice.equals("Daglige smittetal")){
            seriesInfected.getData().clear();
            for (int i = 300; i > 0; i--) { // create graph for infected over time
                int index = keyNumbers.size() - i - 1;
                if (!(Integer.parseInt(keyNumbers.get(index).getNewConfirmedCases()) < 0)) {
                    seriesInfected.getData().add(new XYChart.Data(keyNumbers.get(index).getDate(), Integer.valueOf(keyNumbers.get(index).getNewConfirmedCases())));
                }
            }
            //add data to graph
            infectedDevelopment.getData().addAll(seriesInfected);
        }
        else{ // create graph for dead count over time
            seriesDeathByDay.getData().clear();
            for (int i = 300; i > 0; i--) {
                int index = keyNumbers.size() - i - 1;
                if (!(Integer.parseInt(keyNumbers.get(index).getNewConfirmedCases()) < 0)) {
                    seriesDeathByDay.getData().add(new XYChart.Data(keyNumbers.get(index).getDate(), Integer.valueOf(keyNumbers.get(index).getDeathByDay())));
                }
            }
            //add data to graph
            infectedDevelopment.getData().addAll(seriesDeathByDay);
        }
    }

    // Set labels for keynumbers based on date picked (event)
    @FXML
    void getKeyNumbers1(ActionEvent event) {
        boolean flag = false;
        this.date = datePicker.getValue().toString();

        int counter = 0;
        for (KeyNumbers i : keyNumbers) {
            if (i.getDate().equals(date)) { // set keynumbers for the current date
                infected.setText(keyNumbers.get(counter).getConfirmedCases());
                testedTotal.setText(keyNumbers.get(counter).getTestedTotal());
                hospitalized.setText(keyNumbers.get(counter).getHospitalized());
                intensive.setText(keyNumbers.get(counter).getIntensiveCare());
                respirator.setText(keyNumbers.get(counter).getRespirator());
                deathCount.setText(keyNumbers.get(counter).getDeathCount());
                deathByDay.setText(keyNumbers.get(counter).getDeathByDay());
                infectedByDay.setText(keyNumbers.get(counter).getNewConfirmedCases());
                flag = true; // if data for date exists, flag is true
            }
            counter++;
        }
        if (!flag){ // if flag is false, data for date does not exist
            // clear labels, and tell user
            infected.setText("");
            testedTotal.setText("");
            hospitalized.setText("");
            intensive.setText("");
            respirator.setText("");
            deathCount.setText("");
            deathByDay.setText("");
            infectedByDay.setText("");
            extraInfoListView.getItems().clear();
            dateException.setText("Der findes ikke data fra denne dato");
        }
    }
    /*
    What actually happens behind Scenebuilder:

    communesListView.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        ....functionality
        }
    });
    */
    /*
    with lambda expression:
    communesListView.setOnAction(event -> ...functionality);

     */
    // when certain commune of listview is clicked on, create new piechart
    @FXML
    public void listAgeGroup(MouseEvent event) {
        boolean flag = false;

        // typecast selected item to String
        String commune = (String) communesListView.getSelectionModel().getSelectedItem();
        // display commune over piechart
        communeLabel1.setText(commune);

        for (ConfirmedCasesAgeGroup i : confirmedCasesAgeGroups) {
            if (commune.equals(communeCodeHashMap.get(i.getCommune()))) { // create piechart data for specific commune
                tmpAgeGroupDistribution.getAgeGroupDistribution(i.getAgeGroup(), i.getConfirmedCases());
                tmpAgeGroupDistribution.getCases();
                flag = true; /* if commune is not in confirmedCasesAgeGroups,
                                either no data is available, or the commune does not exist
                              */
            }
        }
        // handle if some agegroups are not initalized
        tmpAgeGroupDistribution.setNullPointers();
        // if commune doesen't exist or no data is available, tell user
        if (!flag){
            invalidInput.setText("Denne kommune eksisterer ikke,\neller der er ikke noget data fra kommunen!");
            // either way reset cases in tmpAgeGroupDistribution
            tmpAgeGroupDistribution.setCases(0);
        }
        else { // create piechart
            invalidInput.setText("");
            ObservableList<PieChart.Data> pieChartData = tmpAgeGroupDistribution.getPieChartData();
            ageGroupDistribution.setData(pieChartData);
            tmpAgeGroupDistribution.setCases(0);
            // also set setPositiveProcentListView, which is based on commune
            setPositiveProcentListView(commune);
        }
    }

    // set extra information on request
    @FXML
    public void setExtraInformation(ActionEvent actionEvent) {
        extraInfoListView.getItems().clear();
        for (KeyNumbers i : keyNumbers) {
            if (i.getDate().equals(this.date)) {
                // the rest of the data in keyNumbers
                extraInfoListView.getItems().addAll("PCR tests: " + i.getDaily_PCR_tests());
                extraInfoListView.getItems().addAll("Antigen tests: " + i.getTaily_Antigen_tests());
                extraInfoListView.getItems().addAll("Registreret raske: " + i.getCovidNoMoreByDay());
                extraInfoListView.getItems().addAll("Testede første gang: " + i.getTestedFirstTime());
                extraInfoListView.getItems().addAll("Forskel i respirator: " + i.getChangeInRespirator());
                extraInfoListView.getItems().addAll("Forskel på intensiv: " + i.getChangeInIntensiveCare());
                extraInfoListView.getItems().addAll("Total antal AG tests: " + i.getTest_totalAG());
                extraInfoListView.getItems().addAll("Total antal PCR tests: " + i.getTest_totalPCR());

            }
        }
    }

    // search function, based on value in textField
    @FXML
    void getCommune(ActionEvent event) {
        boolean flag = true;
        String communeTest = null;
        String commune = null;

        // clear invalidInput, since this might be a valid commune
        invalidInput.setText("");
        Iterator<String> iterator = communeCodes.keySet().iterator(); // iterate over communecodes hashmap
        for (int i = 0; i < communeCodes.size(); i++){
            communeTest = communeCodes.get(iterator.next());
            if (textField.getText().equalsIgnoreCase(communeTest)){ // if commune is valid
                commune = communeTest; // set correct commune
                flag = false;
            }
        }


        if (flag){ // if flag is true, if statement above is never entered and flag remains true
            invalidInput.setText("Denne kommune eksisterer ikke,\neller der er ikke noget data fra kommunen!");
            return; // exit function
        }

        for (ConfirmedCasesAgeGroup j : confirmedCasesAgeGroups) {
            if (textField.getText().equalsIgnoreCase(communeCodeHashMap.get(j.getCommune()))) { // create data for piechart
                tmpAgeGroupDistribution.getAgeGroupDistribution(j.getAgeGroup(), j.getConfirmedCases());
                tmpAgeGroupDistribution.getCases();
            }
        }

        tmpAgeGroupDistribution.setNullPointers();
        ObservableList<PieChart.Data> pieChartData = tmpAgeGroupDistribution.getPieChartData();
        ageGroupDistribution.setData(pieChartData);
        communeLabel1.setText(commune);
        setPositiveProcentListView(commune);

        tmpAgeGroupDistribution.setCases(0);
    }

    // function used in listAgeGroup
    private void setPositiveProcentListView(String commune){
        positiveProcentListView.getItems().clear();
        for (Map i : mapArrayList) {
            if (communeCodeHashMap.get(i.getCommune()).equals(commune)) {
                communeLabel.setText(commune);
                positiveProcentListView.getItems().addAll("Positiv procent: " + i.getPositivePercent());
                positiveProcentListView.getItems().addAll("Nye smittetilfaelde: " + i.getConfirmedCases());
                positiveProcentListView.getItems().addAll("Testede de sidste 7 dage: " + i.getTests7Days());
            }
        }
    }

    /* changeSceneToRegions and changeSceneToCommunes are connected to buttons which switches scenes
       created in two different fxml files and controllers.
     */
    @FXML
    void changeSceneToRegions(ActionEvent event) throws IOException {
        Parent regionsParent = FXMLLoader.load(getClass().getResource("Regions.fxml"));
        //Scene regionsScene = new Scene(regionsParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(regionsParent));

        //window.setScene(regionsScene);
        window.show();
    }

    @FXML
    void changeSceneToCommunes(ActionEvent event) throws IOException {
        Parent communesParent = FXMLLoader.load(getClass().getResource("Communes.fxml"));
        //Scene communesScene = new Scene(communesParent);


        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(communesParent));

        //window.setScene(communesScene);
        window.show();

        // Scene -> Node -> Parent
    }
}
