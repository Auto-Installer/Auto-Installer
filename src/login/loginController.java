package login;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;



// Handles logins

public class loginController {
    @FXML
    Text autoInstallerText;
    @FXML
    Pane loader;
    @FXML
    private AnchorPane ap;



    public void animateLoader() throws InterruptedException{

        FadeTransition ft = new FadeTransition(Duration.millis(2000), ap);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);

        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                routeToHome();
            }

        });


    }

    private Scene homeScene;

    public void setHomeScene(Scene scene){
        homeScene = scene;
    }


    public void routeToHome(){
        Stage primaryStage = (Stage) ap.getScene().getWindow();
        primaryStage.setScene(homeScene);
    }

    public void initialize(){
        try{
            animateLoader();
        }catch (Exception e){
            System.out.println("Error: " + e);
        }

    }


}
