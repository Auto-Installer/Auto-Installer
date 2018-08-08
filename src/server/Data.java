package server;

import home.Program;

import com.fasterxml.jackson.core.JsonParseException;
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
            String programsDataUrl = "./programs.json";
            File programsJson = new File(programsDataUrl);
            System.out.println("Attempting to read from file in: "+ programsJson.getCanonicalPath());
            Program programsData = mapper.readValue(programsJson, Program.class);
            System.out.println(programsData.name);
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
