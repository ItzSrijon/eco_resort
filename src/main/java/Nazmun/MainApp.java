package Nazmun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ManagerLogin1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("DuSai Eco Resort");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}