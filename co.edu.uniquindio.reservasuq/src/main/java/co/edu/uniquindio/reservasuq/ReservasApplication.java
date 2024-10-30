package co.edu.uniquindio.reservasuq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReservasApplication.class.getResource("/registro.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ReservasUQ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(ReservasApplication.class, args);
    }
}