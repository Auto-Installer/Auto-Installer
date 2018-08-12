package home;
import javafx.scene.control.Button;
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
    @FXML Pane programSelectionDisplay;
    private static final String SOFTWARE_CLASS = "software" ;



    //home.ProgramList programs  = getPrograms();
    @FXML
    protected void initialize() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        Data data = new Data();

        var programsData = data.getPrograms();
        try {

            //Gets the properties for all of the gaming applications
            for (int i = 0; programsData.gamingApplications.length > i; i++) {
                Object selectedProgram = programsData.getGamingApplications()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);
                programSelectionDisplay.getChildren().add(new Button((program.name).toString()));
                System.out.println(program.name + " " + program.version);
            }

            // Gets the properties for all of the IDE applications
            for (int i = 0; programsData.IDEs.length > i; i++) {
                Object selectedProgram = programsData.getIDEs()[i];
                String programJSON = mapper.writeValueAsString(selectedProgram);
                Program program = mapper.readValue(programJSON, Program.class);
                var softwareNode = new Button((program.name).toString());
                softwareNode.getStyleClass().add(SOFTWARE_CLASS);
                programSelectionDisplay.getChildren().add(softwareNode);
                System.out.println(program.name + " " + program.version);
            }
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }









}
