import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.loginController;
import home.homeController;
import javafx.scene.image.Image;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(System.getProperty("os.name"));

        // Sets Login Stage
        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("./login/login.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 1280, 800);

        // Sets Home Stage
        FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("./home/home.fxml"));
        Parent homePane = homePaneLoader.load();
        Scene homeScene = new Scene(homePane, 1280, 800);

        // injecting home scene into the controller of the login scene
        loginController loginPaneController = loginPaneLoader.getController();
        loginPaneController.setHomeScene(homeScene);


        primaryStage.getIcons().add(new Image("./icon.png"));
        primaryStage.setTitle("Auto-Installer");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
