package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Loads in our design
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        // Sets up the Stage/Scene
        primaryStage.setTitle("Auto Installer");
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        // Gives Java Version
        System.out.println(System.getProperty("java.version"));
        launch(args);
    }
}