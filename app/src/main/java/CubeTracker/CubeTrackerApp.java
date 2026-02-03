package CubeTracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import CubeTracker.ui.ViewManager;

public class CubeTrackerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManager viewManager = new ViewManager(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CubeTracker/ui/fxml/main.fxml"));
        loader.setControllerFactory(viewManager::createController);
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/CubeTracker/ui/css/style.css").toExternalForm());

        primaryStage.setTitle("CubeTracker - WCA Data Explorer");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
