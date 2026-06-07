package org.unischeduler.ui.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppNavigator.setPrimaryStage(stage);

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/ui/fxml/pages/auth/LoginView.fxml"
                )
        );

        Scene scene = new Scene(root, 1400, 800);

        stage.setTitle("UniScheduler");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}