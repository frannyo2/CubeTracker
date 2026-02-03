package CubeTracker.ui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CubeTracker.models.Event;
import CubeTracker.ui.ViewManager;
import CubeTracker.ui.services.EventsService;

public class EventsController {
    private final ViewManager viewManager;
    private final EventsService eventsService;

    @FXML private ProgressIndicator loadingIndicator;
    @FXML private TableView<Event> eventsTable;
    @FXML private TableColumn<Event, String> idColumn;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, String> formatColumn;
    @FXML private Label errorLabel;

    public EventsController(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.eventsService = new EventsService(viewManager.getWcaClient());
    }

    @FXML
    public void initialize() {
        loadingIndicator.setVisible(false);
        errorLabel.setVisible(false);

        loadingIndicator.visibleProperty().bind(eventsService.runningProperty());

        idColumn.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getName()));
        formatColumn.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getFormat()));

        eventsService.setOnSucceeded(e -> displayEvents(eventsService.getValue()));
        eventsService.setOnFailed(e -> showError(eventsService.getException().getMessage()));

        loadEvents();
    }

    private void loadEvents() {
        errorLabel.setVisible(false);
        eventsService.restart();
    }

    private void displayEvents(Event[] events) {
        eventsTable.setItems(FXCollections.observableArrayList(events));
        errorLabel.setVisible(false);
    }

    private void showError(String message) {
        errorLabel.setText("Error: " + message);
        errorLabel.setVisible(true);
    }
}
