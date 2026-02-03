package CubeTracker.ui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CubeTracker.models.Continent;
import CubeTracker.ui.ViewManager;
import CubeTracker.ui.services.ContinentsService;

public class ContinentsController {
    private final ViewManager viewManager;
    private final ContinentsService continentsService;

    @FXML private ProgressIndicator loadingIndicator;
    @FXML private TableView<Continent> continentsTable;
    @FXML private TableColumn<Continent, String> idColumn;
    @FXML private TableColumn<Continent, String> nameColumn;
    @FXML private Label errorLabel;

    public ContinentsController(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.continentsService = new ContinentsService(viewManager.getWcaClient());
    }

    @FXML
    public void initialize() {
        loadingIndicator.setVisible(false);
        errorLabel.setVisible(false);

        loadingIndicator.visibleProperty().bind(continentsService.runningProperty());

        idColumn.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getName()));

        continentsService.setOnSucceeded(e -> displayContinents(continentsService.getValue()));
        continentsService.setOnFailed(e -> showError(continentsService.getException().getMessage()));

        loadContinents();
    }

    private void loadContinents() {
        errorLabel.setVisible(false);
        continentsService.restart();
    }

    private void displayContinents(Continent[] continents) {
        continentsTable.setItems(FXCollections.observableArrayList(continents));
        errorLabel.setVisible(false);
    }

    private void showError(String message) {
        errorLabel.setText("Error: " + message);
        errorLabel.setVisible(true);
    }
}
