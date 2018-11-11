package home;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.Data;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import home.ProgramList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.Program;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static install.installController.setSoftwaresList;


public class homeController {
    private Data data = new Data();

    private GridPane softwareDisplay = new GridPane();

    private static List softwareToBeInstalled =  new ArrayList();

    private ObjectMapper mapper = new ObjectMapper();

    // FXML Objects
    @FXML Pane programSelectionDisplay;

    @FXML AnchorPane softwaresPane;

    // Displays developerIdes if selected
    @FXML private void selectedDeveloperIDEs(){
        selectedSoftwareCategory = "DeveloperIDEs";
        displayDeveloperIDEs();
    }

    // Displays gamingApplications if selected
    @FXML private void selectedGamingApplications(){
        selectedSoftwareCategory = "GamingApplications";
        displayGamingApplications();
    }


    private String selectedSoftwareCategory = "DeveloperIDEs";

    // Displays Applications based on the Category Selected
    private void displayGamingApplications(){
        var gridX = -1;
        var gridY = 0;

        softwareDisplay.getChildren().clear();
        softwareDisplay.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        softwareDisplay.setVgap(10); //vertical gap in pixels
        softwareDisplay.setPadding(new Insets(10,10,10,10)); //margins around the whole grid

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        var programsData = data.getPrograms();

        //Gets the properties for all of the gaming applications
        try{
            for (int i = 0; programsData.gamingApplications.length > i; i++) {

                Object selectedProgram = programsData.getGamingApplications()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);


                Pane softwareContainer = createSoftwareNode(program);


                // Determines in which cell the software will be displayed in
                if(gridX < 4){
                    gridX++;

                }else{
                    gridX = 0;
                    gridY++;
                }

                System.out.println(gridX + " " + gridY);
                softwareDisplay.add(softwareContainer, gridX, gridY); // column, row

                System.out.println(program.name + " " + program.version);
            }
        }catch (JsonParseException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayDeveloperIDEs() {

        var gridX = -1;
        var gridY = 0;

        softwareDisplay.getChildren().clear();
        softwareDisplay.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        softwareDisplay.setVgap(10); //vertical gap in pixels
        softwareDisplay.setPadding(new Insets(10,10,10,10)); //margins around the whole grid
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        var programsData = data.getPrograms();

        try {
            //Gets the properties for all of the IDE applications
            for (int i = 0; programsData.IDEs.length > i; i++) {

                Object selectedProgram = programsData.getIDEs()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);

                Pane softwareContainer = createSoftwareNode(program);

                // Determines in which cell the software will be displayed in
                if(gridX < 4){
                    gridX++;

                }else{
                    gridX = 0;
                    gridY++;
                }

                softwareDisplay.add(softwareContainer, gridX, gridY); // column, row
                System.out.println(program.name + " " + program.version + " coordinates: (" + gridX + "," + gridY + ")");

            }

        }catch (JsonParseException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method for creating software icons
    public static Pane createSoftwareNode(Program program){
        // Software Pane (Container for everything that goes into the software node)
        Pane softwareContainer = new Pane();
        softwareContainer.setStyle("-fx-background-color: #eaeaea;");
        softwareContainer.setPrefSize(200.0,230.0);

        // Software Image
        ImageView softwareImgContainer = new ImageView();

        Image softwareImg = new Image("./icons/" + program.img_dir);
        softwareImgContainer.setImage(softwareImg);

        System.out.println("Issue loading Image? " + softwareImg.isError());

        softwareImgContainer.setLayoutX(36.0);
        softwareImgContainer.setLayoutY(51.0);
        softwareImgContainer.setFitHeight(128.0);
        softwareImgContainer.setFitWidth(128.0);
        softwareImgContainer.setSmooth(true);
        softwareImgContainer.setCache(true);
        softwareImgContainer.setPreserveRatio(true);



        // Software Selector (CheckBox)
        CheckBox softwareSelectionCheckBox = new CheckBox();

        softwareSelectionCheckBox.setLayoutX(85.0);
        softwareSelectionCheckBox.setLayoutY(179.0);
        softwareSelectionCheckBox.setFont(Font.font(25.0));
        softwareSelectionCheckBox.setCursor(Cursor.HAND);

        // Software Name
        Text softwareName = new Text();
        softwareName.setFont(new Font(22.0));
        softwareName.setText((program.name).toString());
        softwareName.setFill(Color.WHITE);
        softwareName.setLayoutY(30.0);
        softwareName.setTextAlignment(TextAlignment.CENTER);

        // Handles what will occur when the software is selected
        softwareSelectionCheckBox.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(!(softwareSelectionCheckBox.isSelected())){
                    softwareToBeInstalled.add(program);
                    System.out.println("Added " +  program + " to be installed");
                }else{
                    softwareToBeInstalled.remove(program);
                    System.out.println("Removed " +  program + " from being installed");
                }
                setSoftwaresList(softwareToBeInstalled);
            }
        });

        softwareContainer.getChildren().addAll(softwareSelectionCheckBox, softwareName, softwareImgContainer);

        return softwareContainer;

    }

    private Scene installScene;

    public void setInstallScene(Scene scene){

        installScene = scene;
    }


    // Nav Bar
    @FXML private void goToInstallation() {
        System.out.println("Routing to Installation..");
        Stage primaryStage = (Stage) softwaresPane.getScene().getWindow();
        primaryStage.setScene(installScene);
    }

    @FXML
    protected void initialize() {
        // Setting up where the software will be displayed
        programSelectionDisplay.getChildren().addAll(softwareDisplay);
        if (selectedSoftwareCategory == "DeveloperIDEs") {
            displayDeveloperIDEs();
        }
        else if(selectedSoftwareCategory == "GamingApplications") {
            displayGamingApplications();
        }
    }

}
