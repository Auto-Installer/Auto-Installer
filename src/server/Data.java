package server;


import com.dropbox.core.DbxDownloader;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import home.ProgramList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.team.UserSelectorArg;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Data {
    private static final String ACCESS_TOKEN = "Ga6TgeGiiUAAAAAAAAAAghSFY_3xsNKD3u8UKUR4D-DYoSsSjFfoecn1rvrimVnK";
    // Create Dropbox client
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);



    public Data(){}


    public void getDropboxFile(String path) throws DbxException, IOException {

        DbxDownloader<FileMetadata> downloader = client.files().download(path);

        try {
            FileOutputStream out = new FileOutputStream("VsCode.zip");
            downloader.download(out);
            out.close();
        } catch (DbxException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static ProgramList getPrograms(){

        ProgramList programsData = null;
        //map json to program\
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

            // Obj location
            String programsDataUrl = "./programs.json";

            File programsJson = new File(programsDataUrl);
            // Indicates where the programs.json is being looked for
            System.out.println("Attempting to read from file in: "+ programsJson.getCanonicalPath());

            // Obj that contains the programs
            programsData = mapper.readValue(programsJson, ProgramList.class);

            // Gets the properties for all of the gaming applications
//            for(int i=0; programsData.gamingApplications.length > i; i++){
//
//                Object selectedProgram = programsData.getGamingApplications()[i];
//                String programJSON = mapper.writeValueAsString(selectedProgram);
//                Program program = mapper.readValue(programJSON, Program.class);
//
//                System.out.println(program.name + " " + program.version);
//            }
//
//            // Gets the properties for all of the IDE applications
//            for(int i=0; programsData.IDEs.length > i; i++){
//                Object selectedProgram = programsData.getIDEs()[i];
//                String programJSON = mapper.writeValueAsString(selectedProgram);
//                Program program = mapper.readValue(programJSON, Program.class);
//                System.out.println(program.name + " " + program.version);
//            }

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

        return programsData;
    }

}
