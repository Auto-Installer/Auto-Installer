package home;
import javafx.scene.control.Button;
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



public class homeController {
    Data data = new Data();
    GridPane softwareDisplay = new GridPane();

    @FXML Pane programSelectionDisplay;

    private String selectedSoftwareCategory = "DeveloperIDEs";

    private void displayGamingApplications(){
        var gridX = 0;
        var gridY = 0;
        softwareDisplay.getChildren().clear();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        var programsData = data.getPrograms();

        //Gets the properties for all of the gaming applications
        try{
            for (int i = 0; programsData.gamingApplications.length > i; i++) {

                Object selectedProgram = programsData.getGamingApplications()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);

                var softwareNode = new Button((program.name).toString());
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

    @FXML public void selectedDeveloperIDEs(){
        selectedSoftwareCategory = "DeveloperIDEs";
        System.out.println("hello");
        displayDeveloperIDEs();
    }

    @FXML public void selectedGamingApplications(){
        selectedSoftwareCategory = "GamingApplications";
        System.out.println("hello");
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
