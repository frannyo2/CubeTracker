package CubeTracker.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;

import CubeTracker.ui.ViewManager;

import java.io.IOException;

public class MainController {
    private final ViewManager viewManager;

    @FXML private Button profileButton;
    @FXML private Button rankingsButton;
    @FXML private Button eventsButton;
    @FXML private Button continentsButton;
    @FXML private StackPane contentArea;

    private Button activeButton;

    public MainController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @FXML
    public void initialize() {
        // Load profile view by default
        showProfile();
    }

    @FXML
    public void showProfile() {
        loadView("profile.fxml", profileButton);
    }

    @FXML
    public void showRankings() {
        loadView("rankings.fxml", rankingsButton);
    }

    @FXML
    public void showEvents() {
        loadView("events.fxml", eventsButton);
    }

    @FXML
    public void showContinents() {
        loadView("continents.fxml", continentsButton);
    }

    private void loadView(String fxmlName, Button button) {
        try {
            Parent view = viewManager.loadView(fxmlName);
            contentArea.getChildren().setAll(view);
            updateActiveButton(button);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateActiveButton(Button newActive) {
        if (activeButton != null) {
            activeButton.getStyleClass().remove("active");
        }
        activeButton = newActive;
        if (activeButton != null) {
            activeButton.getStyleClass().add("active");
        }
    }
}
