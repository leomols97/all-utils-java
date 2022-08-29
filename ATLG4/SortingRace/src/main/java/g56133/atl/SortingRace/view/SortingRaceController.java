package g56133.atl.SortingRace.view;

import g56133.atl.SortingRace.utils.Observer;
import g56133.atl.SortingRace.model.TypeOfSort;
import g56133.atl.SortingRace.model.Model;
import g56133.atl.SortingRace.model.Sort;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import g56133.atl.SortingRace.utils.Observable;

/**
 * Controller of the sorting race.
 * 
 * @author Leong Paeg-Hing
 */
public class SortingRaceController implements Observer{
    @FXML
    private MenuItem aboutItem;

    @FXML
    private LineChart<Integer, Integer> chart;

    @FXML
    private ChoiceBox<String> configurationChoice;

    @FXML
    private TableColumn<Sort, Integer> durationCol;

    @FXML
    private Label leftStatus;

    @FXML
    private TableColumn<Sort, String> nameCol;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private MenuItem quitItem;

    @FXML
    private Label rightStatus;

    @FXML
    private TableColumn<Sort, Integer> sizeCol;

    @FXML
    private ChoiceBox<String> sortChoice;

    @FXML
    private Button start;

    @FXML
    private TableColumn<Sort, Integer> swapCol;

    @FXML
    private TableView<Sort> table;

    @FXML
    private Spinner<Integer> threadSpinner;

    @FXML
    private Font x3;

    @FXML
    private Color x4;
    
    @FXML
    private void launchSorting(ActionEvent event) {
        this.start.setDisable(true);
        TypeOfSort sortType = getTypeOfSort(this.sortChoice.getValue());
        int nbElements = getNumberElements(this.configurationChoice.getValue());
        int nbThreads = this.threadSpinner.getValue();
        resetProgressBar();
        createSeries(this.sortChoice.getValue());
        this.model.start(sortType, nbElements, nbThreads, nbElements/10);
    }
    
    private Model model;
    private XYChart.Series complexity;
    
    public SortingRaceController(Model model) {
        this.model = model;
        Observable modelO = (Observable) model;
        modelO.registerObserver(this);
    }
    
    public void initialize() {
        completeSortChoice();
        completeDifficulty();
        completeSpinner();
        tableViewCreation();
    }
    
    public void completeSortChoice() {
        ObservableList<String> options = FXCollections.observableArrayList
        ("Tri a bulles", "Tri fusions", "Quick sort", "Tri par insertion");
        this.sortChoice.setValue("Tri a bulles");
        this.sortChoice.setItems(options);
    }
    
    public void completeDifficulty() {
        ObservableList<String> options = FXCollections.observableArrayList
        ("Very easy : 0 - 100", "Easy : 0 - 1000", "Normal : 0 - 10000", 
                "Hard : 0 - 100000", "Very hard : 0 - 1000000");
        this.configurationChoice.setValue("Very easy : 0 - 100");
        this.configurationChoice.setItems(options);
    }
    
    public void completeSpinner() {
        SpinnerValueFactory<Integer> valueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        this.threadSpinner.setValueFactory(valueFactory);
    }
    
    public void tableViewCreation() {
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("TypeOfSort"));
        this.durationCol.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        this.sizeCol.setCellValueFactory(new PropertyValueFactory<>("ArraySize"));
        this.swapCol.setCellValueFactory(new PropertyValueFactory<>("NumberOfOperation"));
    }
    
    public void addTableLine(Sort sort) {
        this.table.getItems().add(sort);
    }
    
    public void resetProgressBar() {
        this.progressBar.setProgress(0);
    }
    
    public void updateProgressBar() {
        double newProgress = this.progressBar.getProgress() + 1.0/10;
        this.progressBar.setProgress(newProgress);
    }
    
    public void numberActiveThreads() {
        this.leftStatus.setText("Active thread : " + Thread.activeCount());
    }
    
    public void executionDurationUpdate(long begin, long end, long duration) {
        DateFormat simple = new SimpleDateFormat("HH:mm:ss:SSS");
        Date dateBegin = new Date(begin);
        Date dateEnd = new Date(end);
        this.rightStatus.setText("Last execution | Begin : " + 
                simple.format(dateBegin) + " - End : " + simple.format(dateEnd) 
                + " - Duration : " + duration/1000000 + " ms");
    }
    
    public void createSeries(String typeOfSort) {
        XYChart.Series line = new XYChart.Series();
        line.setName(typeOfSort);
        this.complexity = line;
        this.chart.getData().add(line);
    }
    
    public void updateSeries(int size, long nbOperation) {
        this.complexity.getData().add(new XYChart.Data(size, nbOperation));
    }
    
    public TypeOfSort getTypeOfSort(String sort) {
        switch(sort) {
            case "Tri a bulles" : return TypeOfSort.BUBBLE;
            case "Tri fusions" : return TypeOfSort.MERGE;
            case "Quick sort" : return TypeOfSort.QUICK;
            case "Tri par insertion" : return TypeOfSort.INSERTION;
            default : return null;
        }
    }
    
    public int getNumberElements(String difficulty) {
        switch(difficulty) {
            case "Very easy : 0 - 100" : return 100;
            case "Easy : 0 - 1000" : return 1000;
            case "Normal : 0 - 10000" : return 10000;
            case "Hard : 0 - 100000" : return 100000;
            case "Very hard : 0 - 1000000" : return 1000000;
            default : return 0;
        }
    }

    @Override
    public void update(List<Object> object) {
        Platform.runLater(() -> {
            Sort sort = (Sort) object.get(0);
            boolean isCompleted = (boolean) object.get(1);
            updateSeries(sort.getArraySize(), sort.getNumberOfOperation());
            addTableLine(sort);
            updateProgressBar();
            numberActiveThreads();
            executionDurationUpdate(sort.getBegin(), sort.getEnd(), 
                    sort.getDuration());
            if(isCompleted) {
                this.start.setDisable(false);
            }
        });
    }
}
