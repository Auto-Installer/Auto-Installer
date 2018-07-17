package login;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;


public class loginController {
    @FXML Button submitLogin;
    @FXML TextField usernameInput;
    @FXML PasswordField passwordInput;

    public void submitLogin(){
        System.out.println(usernameInput.getText());
        System.out.println(passwordInput.getText());

    }

}
