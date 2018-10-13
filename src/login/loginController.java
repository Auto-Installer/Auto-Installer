package login;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
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

        FadeTransition fadeIn = new FadeTransition(Duration.millis(3000), autoInstallerText);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1.0);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(3000), autoInstallerText);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0);


        SequentialTransition seqT = new SequentialTransition(fadeIn, fadeOut);
        seqT.setCycleCount(3);

        seqT.play();

        seqT.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Finished");
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
