package home;
import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
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


public class homeController {
    private Data data = new Data();

    private GridPane softwareDisplay = new GridPane();

    private List softwareToBeInstalled =  new ArrayList();

    private ObjectMapper mapper = new ObjectMapper();

    @FXML Pane programSelectionDisplay;

    @FXML
    Pane softwaresPane;

    private String selectedSoftwareCategory = "DeveloperIDEs";

    private void displayGamingApplications(){
        var gridX = 0;
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

                Pane softwareContainer = new Pane();
                softwareContainer.setStyle("-fx-background-color: #071756; -fx-background-radius: 10px;");
                softwareContainer.setPrefSize(188.0,186.0);

                Text softwareName = new Text();
                softwareName.setFont(new Font(17.0));
                softwareName.setText((program.name).toString());
                softwareName.setFill(Color.WHITE);
                softwareName.setLayoutY(26.0);
                softwareName.setLayoutX(10.0);


                Button selectSoftwareButton = new Button("SELECT");
                selectSoftwareButton.setLayoutX(36.0);
                selectSoftwareButton.setLayoutY(143.0);
                selectSoftwareButton.setStyle("-fx-background-color: #229b24;");

                softwareContainer.getChildren().addAll(selectSoftwareButton, softwareName);


                // Handles what will occur when the software is clicked
                selectSoftwareButton.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        softwareToBeInstalled.add(program);
                        System.out.println(program);
                    }
                });

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

    // Installs programs from dropbox
    public void installSoftwares() throws JsonProcessingException, IOException, DbxException {
        System.out.println(softwareToBeInstalled);
        for(int i=0; i < softwareToBeInstalled.size(); i++ ){
            Object selectedProgram = softwareToBeInstalled.get(i);
            String programJSON = mapper.writeValueAsString(selectedProgram);
            Program program = mapper.readValue(programJSON, Program.class);
            data.getDropboxFile((program.name).toString(), (program.category).toString(), ".zip", (program.exeName).toString());
        }
    }

    public static Pane createSoftwareNode(Program program){
        // Software Pane (Container for everything that goes into the software node)
        Pane softwareContainer = new Pane();
        softwareContainer.setStyle("-fx-background-color: #eaeaea;");
        softwareContainer.setPrefSize(200.0,230.0);

        // Software Image
        ImageView softwareImgContainer = new ImageView();
        softwareImgContainer.setLayoutX(36.0);
        softwareImgContainer.setLayoutY(51.0);
        softwareImgContainer.setFitHeight(128.0);
        softwareImgContainer.setFitWidth(128.0);
        Image softwareImg = new Image("@../vsCode.png");
        softwareImgContainer.setImage(softwareImg);

        // Software Selector (CheckBox)
        CheckBox softwareSelectionButton = new CheckBox();
        softwareSelectionButton.setLayoutX(85.0);
        softwareSelectionButton.setLayoutY(179.0);
        softwareSelectionButton.setFont(Font.font(25.0));
        softwareSelectionButton.setCursor(Cursor.HAND);

        // Software Name
        Text softwareName = new Text();
        softwareName.setFont(new Font(22.0));
        softwareName.setText((program.name).toString());
        softwareName.setFill(Color.WHITE);
        softwareName.setLayoutY(30.0);
        softwareName.setTextAlignment(TextAlignment.CENTER);

        // Handles what will occur when the software is clicked
        softwareSelectionButton.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                softwareToBeInstalled.add(program);
                System.out.println(program);
            }
        });

        softwareContainer.getChildren().addAll(softwareImgContainer, softwareSelectionButton, softwareName);

        return softwareContainer;


    }

    // Displays developerIdes if selected
    @FXML public void selectedDeveloperIDEs(){
        selectedSoftwareCategory = "DeveloperIDEs";
        displayDeveloperIDEs();
    }

    // Displays gamingApplications if selected
    @FXML public void selectedGamingApplications(){
        selectedSoftwareCategory = "GamingApplications";
        displayGamingApplications();
    }

    public void animateLoader() throws InterruptedException{



        FadeTransition fadeInSoftware = new FadeTransition(Duration.millis(3000), softwaresPane);
        fadeInSoftware.setFromValue(0);
        fadeInSoftware.setToValue(1.0);


        fadeInSoftware.play();
        fadeInSoftware.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });


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



        try{
            animateLoader();

        }catch(Exception e){

        }



    }

}
