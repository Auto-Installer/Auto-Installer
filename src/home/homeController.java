package home;
import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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

    private String selectedSoftwareCategory = "DeveloperIDEs";

    private void displayGamingApplications(){
        var gridX = 0;
        var gridY = 0;
        softwareDisplay.getChildren().clear();

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        var programsData = data.getPrograms();

        //Gets the properties for all of the gaming applications
        try{
            for (int i = 0; programsData.gamingApplications.length > i; i++) {

                Object selectedProgram = programsData.getGamingApplications()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);

                // Handles what will occur when the software is clicked
                var softwareNode = new Button((program.name).toString());

                softwareNode.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        softwareToBeInstalled.add(program);
                        System.out.println(program);
                    }
                });

                // Determines in which cell the software will be displayed in
                if(gridX < 3){
                    gridX++;

                }else{
                    gridX = 0;
                    gridY++;
                }

                System.out.println(gridX + " " + gridY);
                softwareDisplay.add(softwareNode, gridX, gridY); // column, row

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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        var programsData = data.getPrograms();
        try {
            //Gets the properties for all of the IDE applications
            for (int i = 0; programsData.IDEs.length > i; i++) {

                Object selectedProgram = programsData.getIDEs()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);

                var softwareNode = new Button((program.name).toString());

                // Handles what will occur when the software is clicked
                softwareNode.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        softwareToBeInstalled.add(program);
                        System.out.println(program);
                    }
                });

                // Determines in which cell the software will be displayed in
                if(gridX < 2){
                    gridX++;

                }else{
                    gridX = 0;
                    gridY++;
                }

                softwareDisplay.add(softwareNode, gridX, gridY); // column, row
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

    public void installSoftwares() throws JsonProcessingException, IOException, DbxException {
        System.out.println(softwareToBeInstalled);
        for(int i=0; i < softwareToBeInstalled.size(); i++ ){
            Object selectedProgram = softwareToBeInstalled.get(i);
            String programJSON = mapper.writeValueAsString(selectedProgram);
            Program program = mapper.readValue(programJSON, Program.class);

            data.getDropboxFile((program.name).toString(), (program.category).toString(), ".zip");
        }
    }

    @FXML public void selectedDeveloperIDEs(){
        selectedSoftwareCategory = "DeveloperIDEs";
        displayDeveloperIDEs();
    }

    @FXML public void selectedGamingApplications(){
        selectedSoftwareCategory = "GamingApplications";
        displayGamingApplications();
    }

    @FXML
    protected void initialize() {
        Data data = new Data();
        // Setting up where the software will be displayed
        programSelectionDisplay.getChildren().addAll(softwareDisplay);

        // Contains the JSON data for the programs
        var programsData = data.getPrograms();

            if (selectedSoftwareCategory == "DeveloperIDEs") {
                displayDeveloperIDEs();
            }
            else if(selectedSoftwareCategory == "GamingApplications") {
                displayGamingApplications();
            }

    }

}
