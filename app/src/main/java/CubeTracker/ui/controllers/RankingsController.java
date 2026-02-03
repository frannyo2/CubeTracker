package CubeTracker.ui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CubeTracker.models.Rank;
import CubeTracker.ui.ViewManager;
import CubeTracker.ui.services.RankingsService;

public class RankingsController {
    private final ViewManager viewManager;
    private final RankingsService rankingsService;

    @FXML private ComboBox<String> rankTypeCombo;
    @FXML private Spinner<Integer> limitSpinner;
    @FXML private Button loadButton;
    @FXML private ProgressIndicator loadingIndicator;
    @FXML private TableView<Rank> rankingsTable;
    @FXML private TableColumn<Rank, String> rankColumn;
    @FXML private TableColumn<Rank, String> nameColumn;
    @FXML private TableColumn<Rank, String> timeColumn;
    @FXML private Label errorLabel;

    public RankingsController(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.rankingsService = new RankingsService(viewManager.getWcaClient());
    }

    @FXML
    public void initialize() {
        rankTypeCombo.setItems(FXCollections.observableArrayList("Single", "Average"));
        rankTypeCombo.setValue("Single");

        SpinnerValueFactory<Integer> valueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 10);
        limitSpinner.setValueFactory(valueFactory);

        loadingIndicator.setVisible(false);
        errorLabel.setVisible(false);

        loadingIndicator.visibleProperty().bind(rankingsService.runningProperty());
        loadButton.disableProperty().bind(rankingsService.runningProperty());

        rankColumn.setCellValueFactory(data -> {
            int[] ranks = data.getValue().getRank();
            return new SimpleStringProperty(String.valueOf(ranks[0]));
        });

        nameColumn.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getPersonId()));

        timeColumn.setCellValueFactory(data ->
            new SimpleStringProperty(formatTime(data.getValue().getBest())));

        rankingsService.setOnSucceeded(e -> displayRankings(rankingsService.getValue()));
        rankingsService.setOnFailed(e -> showError(rankingsService.getException().getMessage()));

        loadRankings();
    }

    @FXML
    public void loadRankings() {
        errorLabel.setVisible(false);
        rankingsTable.getItems().clear();

        boolean isSingle = "Single".equals(rankTypeCombo.getValue());
        int limit = limitSpinner.getValue();

        rankingsService.setSingle(isSingle);
        rankingsService.setLimit(limit);
        rankingsService.restart();
    }

    private void displayRankings(Rank[] rankings) {
        rankingsTable.setItems(FXCollections.observableArrayList(rankings));
        errorLabel.setVisible(false);
    }

    private void showError(String message) {
        errorLabel.setText("Error: " + message);
        errorLabel.setVisible(true);
    }

    private String formatTime(double seconds) {
        if (seconds < 60) {
            return String.format("%.2f", seconds);
        }
        int minutes = (int) (seconds / 60);
        double secs = seconds % 60;
        return String.format("%d:%05.2f", minutes, secs);
    }
}
