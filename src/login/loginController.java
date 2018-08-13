package login;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.awt.*;

// Handles logins

public class loginController {
    @FXML TextField usernameInput;
    @FXML PasswordField passwordInput;

    private Scene homeScene;

    public void setHomeScene(Scene scene){
        homeScene = scene;
    }

    public void routeToHome(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(homeScene);
    }

    public void submitLogin(ActionEvent actionEvent){

        if((usernameInput.getText().equals("john")) && (passwordInput.getText().equals("doe"))) {
            System.out.println("Successfully logged in!");
            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(homeScene);
        }
        else{
            System.out.println("Those credentials were wrong");
        }

    }



}
