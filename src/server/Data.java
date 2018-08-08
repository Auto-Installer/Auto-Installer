package server;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.Program;

import java.io.File;
import java.io.IOException;

public class Data {
    public Data(){}

    public static String getPrograms(){
        ObjectMapper mapper = new ObjectMapper();
        String programsDataUrl = "./programs.json";

        //map json to program
        try{
            File programsJson = new File(programsDataUrl);
            System.out.println("Attempting to read from file in: "+ programsJson.getCanonicalPath());
            Program programsData = mapper.readValue(programsJson, Program.class);
            System.out.println(programsData);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "works";
    }

}
