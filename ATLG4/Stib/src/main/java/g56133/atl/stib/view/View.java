package g56133.atl.stib.view;

import g56133.atl.stib.handler.AddFavoritehandler;
import g56133.atl.stib.handler.ChangeFavoNameHandler;
import g56133.atl.stib.handler.ChangeFavoStationsHandler;
import g56133.atl.stib.handler.DeleteFavoriteHandler;
import g56133.atl.stib.handler.SearchFavoriteHandler;
import g56133.atl.stib.handler.SearchHandler;
import g56133.atl.stib.model.core.Node;
import g56133.atl.stib.presenter.Presenter;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author Leong Paeg-Hing
 */
public class View {
    
    @FXML
    private Button AddFavorites;

    @FXML
    private Button ButtonFavorites;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button ChangeNameButton;

    @FXML
    private Button ChangeStationButton;

    @FXML
    private Label DestinationLabel;

    @FXML
    private TableColumn<Node, String> LineColumn;

    @FXML
    private VBox MenuBox;

    @FXML
    private ImageView MetroMap;

    @FXML
    private Label OriginLabel;

    @FXML
    private Label OriginLabel1;

    @FXML
    private TableView<Node> ResultTable;

    @FXML
    private ScrollPane ScrollImage;

    @FXML
    private SearchableComboBox<String> SearchDestination;

    @FXML
    private SearchableComboBox<String> SearchFavorites;

    @FXML
    private SearchableComboBox<String> SearchOrigin;

    @FXML
    private TableColumn<Node, String> StationColumn;

    @FXML
    private ImageView StibLogo;

    @FXML
    private Button searchButton;
    
    public View() {}
    
    public void initialize() { // call when load
        setUpTableView();
    }
    
    private void setUpTableView() {
        this.StationColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.LineColumn.setCellValueFactory(new PropertyValueFactory<>("LinesToString"));
    }
    
    public void setUpStation(List<String> stationName) {
        ObservableList<String> stations = FXCollections.observableArrayList(stationName);
        this.SearchOrigin.setItems(stations);
        this.SearchDestination.setItems(stations);
    }
    
    public void setUpFavorites(List<String> favoritesName) {
        ObservableList<String> favorites = FXCollections.observableArrayList(favoritesName);
        this.SearchFavorites.setItems(favorites);
    }
    
    public void setUpHandler(Presenter presenter) {
        addHandlerButton(presenter);
        addHandlerButtonFavorite(presenter);
        addHandlerButtonAddFavorite(presenter);
        addHandlerButtonDeleteFavorite(presenter);
        addHandlerChangeFavoName(presenter);
        addHandlerChangeFavoStations(presenter);
    }
    
    private void addHandlerButton(Presenter presenter) {
        SearchHandler handler = new SearchHandler(this, presenter);
        searchButton.setOnAction(handler);
    }
    
    private void addHandlerButtonFavorite(Presenter presenter) {
        SearchFavoriteHandler handler = new SearchFavoriteHandler(this, presenter);
        ButtonFavorites.setOnAction(handler);
    }
    
    private void addHandlerButtonAddFavorite(Presenter presenter) {
        AddFavoritehandler handler = new AddFavoritehandler(this, presenter);
        AddFavorites.setOnAction(handler);
    }
    
    private void addHandlerButtonDeleteFavorite(Presenter presenter) {
        DeleteFavoriteHandler handler = new DeleteFavoriteHandler(this, presenter);
        deleteButton.setOnAction(handler);
    }
    
    private void addHandlerChangeFavoName(Presenter presenter) {
        ChangeFavoNameHandler handler = new ChangeFavoNameHandler(this, presenter);
        ChangeNameButton.setOnAction(handler);
    }
    
    private void addHandlerChangeFavoStations(Presenter presenter) {
        ChangeFavoStationsHandler handler = new ChangeFavoStationsHandler(this, presenter);
        ChangeStationButton.setOnAction(handler);
    }
    
    public String askfavoriteName() {
        TextInputDialog td = new TextInputDialog("Enter the name");
        td.setHeaderText("Enter the name of the new favorite.");
        td.setContentText("Name : ");
        td.showAndWait();
        String name = td.getEditor().getText();
        td.close();
        return name;
    }
    
    public void DisplayErrorMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public String getOrigin() {
        return this.SearchOrigin.getValue();
    }
    
    public String getDestination() {
        return this.SearchDestination.getValue();
    }
    
    public String getFavorite() {
        return this.SearchFavorites.getValue();
    }
    
    public void displayPath(Node destination) {
        Platform.runLater(() -> {
            ResultTable.getItems().clear();
            List<Node> path = destination.getShortestPath();
            for(Node n : path) {
                ResultTable.getItems().add(n);
            }
            ResultTable.getItems().add(destination);
        });
    }
    
    public void addFavorite(String favorite) {
        Platform.runLater(() -> {
            if(!this.SearchFavorites.getItems().contains(favorite)) {
                ObservableList<String> fav = this.SearchFavorites.getItems();
                fav.add(favorite);
                this.SearchFavorites.setItems(fav);
            }
        });
    }
    
    public void updateFavoriteList(List<String> favorite) {
        Platform.runLater(() -> {
            this.SearchFavorites.setValue("");
            ObservableList<String> fav = FXCollections.observableArrayList(favorite);
            this.SearchFavorites.setItems(fav);
        });
    }
    
    public void setStations(List<String> favorites) {
        Platform.runLater(() -> {
            this.SearchOrigin.setValue(favorites.get(0));
            this.SearchDestination.setValue(favorites.get(1));
        });
    }
}
