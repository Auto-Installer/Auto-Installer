package install;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class installController {
    private Scene softwaresScene;

    @FXML AnchorPane anchorPane;

    public void setInstallScene(Scene scene){
        softwaresScene = scene;
    }

    @FXML private void routeToSoftwares(){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(softwaresScene);
        System.out.println("Routing to softwares..");
    }


}
