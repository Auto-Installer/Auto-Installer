package install;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class installController {
    private Scene softwaresScene;

    @FXML AnchorPane anchorPane;

    private static List softwareList;

    public static void setSoftwaresList(List softwares){
        softwareList = softwares;
        System.out.println(softwareList);
    }

    public void setInstallScene(Scene scene){
        softwaresScene = scene;
    }

    @FXML private void routeToSoftwares(){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(softwaresScene);
        System.out.println("Routing to softwares..");
    }


}
