package CubeTracker.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import CubeTracker.models.Person;
import CubeTracker.ui.ViewManager;
import CubeTracker.ui.services.ProfileService;

public class ProfileController {
    private final ViewManager viewManager;
    private final ProfileService profileService;

    @FXML private TextField wcaIdField;
    @FXML private Button searchButton;
    @FXML private ProgressIndicator loadingIndicator;
    @FXML private VBox resultsBox;
    @FXML private Label errorLabel;

    @FXML private Label nameLabel;
    @FXML private Label idLabel;
    @FXML private Label countryLabel;
    @FXML private Label competitionsLabel;
    @FXML private Label medalsLabel;

    public ProfileController(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.profileService = new ProfileService(viewManager.getWcaClient());
    }

    @FXML
    public void initialize() {
        loadingIndicator.setVisible(false);
        resultsBox.setVisible(false);
        errorLabel.setVisible(false);

        loadingIndicator.visibleProperty().bind(profileService.runningProperty());
        searchButton.disableProperty().bind(profileService.runningProperty());

        profileService.setOnSucceeded(e -> displayPerson(profileService.getValue()));
        profileService.setOnFailed(e -> showError(profileService.getException().getMessage()));

        wcaIdField.setOnAction(e -> search());
    }

    @FXML
    public void search() {
        String wcaId = wcaIdField.getText().trim().toUpperCase();
        if (wcaId.isEmpty()) {
            showError("Please enter a WCA ID");
            return;
        }

        errorLabel.setVisible(false);
        resultsBox.setVisible(false);

        profileService.setWcaId(wcaId);
        profileService.restart();
    }

    private void displayPerson(Person person) {
        nameLabel.setText(person.getName());
        idLabel.setText(person.getId());
        countryLabel.setText(person.getCountry());
        competitionsLabel.setText(String.valueOf(person.getNumberOfCompetitions()));

        Person.Medals medals = person.getMedals();
        if (medals != null) {
            medalsLabel.setText(String.format("Gold: %d | Silver: %d | Bronze: %d",
                medals.getGold(), medals.getSilver(), medals.getBronze()));
        } else {
            medalsLabel.setText("No medals");
        }

        resultsBox.setVisible(true);
        errorLabel.setVisible(false);
    }

    private void showError(String message) {
        errorLabel.setText("Error: " + message);
        errorLabel.setVisible(true);
        resultsBox.setVisible(false);
    }
}
