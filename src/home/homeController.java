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

    private String selectedSoftwareCatergory = "DeveloperIDEs";

    private void displayDeveloperIDEs(){
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
                softwareDisplay.add(softwareNode, i, i); // column, row
                //programSelectionDisplay.getChildren().addAll(softwareNode);
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

    private void displayGamingApplications() {

        softwareDisplay.getChildren().clear();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        var programsData = data.getPrograms();
        try {
            //Gets the properties for all of the IDE applications
            for (int i = 0; programsData.IDEs.length > i; i++) {
                //i = 10;
                Object selectedProgram = programsData.getIDEs()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);
                var softwareNode = new Button((program.name).toString());
                softwareDisplay.add(softwareNode, i, i); // column, row
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

    @FXML public void selectedDeveloperIDEs(){
        selectedSoftwareCatergory = "DeveloperIDEs";
        System.out.println("hello");
        displayDeveloperIDEs();
    }

    @FXML public void selectedGamingApplications(){
        selectedSoftwareCatergory = "GamingApplications";
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

            if (selectedSoftwareCatergory == "DeveloperIDEs") {
                displayDeveloperIDEs();
            }
            else if(selectedSoftwareCatergory == "GamingApplications") {
                displayGamingApplications();
            }

    }

}
