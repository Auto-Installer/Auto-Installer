package server;

import home.ProgramList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.io.File;
import java.io.IOException;




public class Data {
    public Data(){}

    public static String getPrograms(){
        //map json to program
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

            String programsDataUrl = "./programs.json";

            File programsJson = new File(programsDataUrl);
            System.out.println("Attempting to read from file in: "+ programsJson.getCanonicalPath());

            ProgramList programsData = mapper.readValue(programsJson, ProgramList.class);
            Object x = programsData.getGamingApplications()[0];

            String programJSON = mapper.writeValueAsString(x);
            Program y = mapper.readValue(programJSON, Program.class);
            System.out.println(y.name);



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

        return "works";
    }

}
