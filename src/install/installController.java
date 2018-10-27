package install;
import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.Program;
import server.Data;

import java.io.IOException;
import java.util.List;
import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;


public class installController {
    private Scene softwaresScene;
    private ObjectMapper mapper = new ObjectMapper();
    private Data data = new Data();

    private @FXML AnchorPane anchorPane;
    public @FXML ProgressBar installationProgress;
    public @FXML Text progressText;

    private static List softwareList;

    public static void setSoftwaresList(List softwares){
        softwareList = softwares;
        System.out.println(softwareList);
    }



    // Installs programs from dropbox
    @FXML private void installSoftwares() throws JsonProcessingException, IOException, DbxException {
        System.out.println(softwareList);
        for(int i=0; i < softwareList.size(); i++ ){
            Object selectedProgram = softwareList.get(i);
            String programJSON = mapper.writeValueAsString(selectedProgram);
            Program program = mapper.readValue(programJSON, Program.class);
            data.getDropboxFile((program.name).toString(), (program.category).toString(), ".zip", (program.exeName).toString(), installationProgress, progressText);
        }
    }

    public void setInstallScene(Scene scene){
        softwaresScene = scene;
    }

    @FXML private void routeToSoftwares(){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(softwaresScene);
        System.out.println("Routing to softwares..");
    }

    @FXML
    protected void initialize() {

        System.out.println("yeet");
    }

}
