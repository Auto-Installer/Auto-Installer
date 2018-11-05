package server;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import home.ProgramList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import mslinks.ShellLink;
import install.installController;

public class Data {

    private static final String ACCESS_TOKEN = "Ga6TgeGiiUAAAAAAAAAAghSFY_3xsNKD3u8UKUR4D-DYoSsSjFfoecn1rvrimVnK";
    private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    private UnzipUtility unzipUtility = new UnzipUtility();
    private ShellLink shellLink = new ShellLink();



    //throws DbxException, IOException
    public Data(){}

    // Updates the installation progress bar
    public void updateProgressBar(ProgressBar progressBar,double progress){
        Thread progressThread = new Thread(){
            public void run(){
                progressBar.setProgress(progress);
                System.out.println("Progress: " + (progress * 100) + "%");
            }
        };
        progressThread.start();
    }

    // updates the progress text
    public void updateProgressText(Text progressText, String progressMsg){
        Thread progressTextThread = new Thread(){
            public void run(){
                progressText.setText(progressMsg);
                System.out.println(progressMsg);
            }

        };
        progressTextThread.start();
    }

    // Downloads the needed software from dropbox
    public void getDropboxFile(String name, String category, String fileType, String exeName, ProgressBar progressBar, Text progressText) {

        Thread.UncaughtExceptionHandler ueh = new Thread.UncaughtExceptionHandler(){
            public void uncaughtException(Thread th, Throwable ex) {
                System.out.println("Uncaught exception: " + ex);
            }
        };

        Thread installationThread = new Thread(){
            public void run() {

                try {
                    String home = System.getProperty("user.home");
                    String root = "C:\\";
                    String ProgramFiles = root + "Program Files/";
                    String shortCutpath = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";

                    // Location in dropbox where the software is located
                    String dropboxPath = "/Software/" + category + "/" + name + fileType;
                    // Download location
                    String zipPath = ProgramFiles + name + fileType;

                    DbxDownloader<FileMetadata> downloader = client.files().download(dropboxPath);

                    updateProgressBar(progressBar, 0);
                    updateProgressText(progressText, "Installing " + name + "...");
                    String downloadPath = ProgramFiles + name;
                    String exePath = downloadPath + "/" + exeName;

                    FileOutputStream out = new FileOutputStream( ProgramFiles + name + fileType);
                    downloader.download(out);
                    out.close();

                    updateProgressBar(progressBar, 0.2);

                    updateProgressText(progressText,"Installed .zip for " + name);
                    updateProgressBar(progressBar, 0.4);

                    new File(downloadPath).mkdirs();

                    updateProgressText(progressText,"Created folder for " + name + ", beginning to unzip the installed .zip");
                    updateProgressBar(progressBar, 0.6);

                    unzipUtility.extractFolder(zipPath, downloadPath);

                    updateProgressText(progressText,"creating shortcut from " + exePath);
                    shellLink.createLink(exePath, shortCutpath + "\\" + name + ".lnk");
                    updateProgressBar(progressBar, 0.8);

                    updateProgressText(progressText,"Successfully installed " + name);
                    updateProgressBar(progressBar, 1);

                } catch (DbxException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (IOException ex){
                    System.out.println(ex.getMessage());
                }

            }
        };

        installationThread.setUncaughtExceptionHandler(ueh);
        installationThread.start();
    }




    public static ProgramList getPrograms(){

        ProgramList programsData = null;

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
