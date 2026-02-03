package CubeTracker.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import CubeTracker.api.WcaClient;
import CubeTracker.ui.controllers.*;

import java.io.IOException;

public class ViewManager {
    private final Stage primaryStage;
    private final WcaClient wcaClient;

    public ViewManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.wcaClient = new WcaClient();
    }

    public WcaClient getWcaClient() {
        return wcaClient;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Object createController(Class<?> type) {
        if (type == MainController.class) {
            return new MainController(this);
        } else if (type == ProfileController.class) {
            return new ProfileController(this);
        } else if (type == RankingsController.class) {
            return new RankingsController(this);
        } else if (type == EventsController.class) {
            return new EventsController(this);
        } else if (type == ContinentsController.class) {
            return new ContinentsController(this);
        }
        throw new IllegalArgumentException("Unknown controller type: " + type);
    }

    public Parent loadView(String fxmlName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CubeTracker/ui/fxml/" + fxmlName));
        loader.setControllerFactory(this::createController);
        return loader.load();
    }
}
