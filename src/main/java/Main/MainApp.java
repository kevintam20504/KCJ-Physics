package Main;

import Controllers.FXMLMainMenuController;
import java.util.logging.Level;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//hello
public class MainApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            // Load the scene of the primary stage.
            Parent root = loadFXML("MainMenu", new FXMLMainMenuController());
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            // Put this appliation's main window on top of other already-opened windows
            // upon launching the app.
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
            primaryStage.setTitle("KCJ Physics");
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Changes the primary stage's current scene.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     */
    public static void switchScene(String fxmlFile, Object fxmlController) {
        try {
            scene.setRoot(loadFXML(fxmlFile, fxmlController));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads a scene graph from an FXML file.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     * @return The root node of the loaded scene graph.
     * @throws IOException
     */
    public static Parent loadFXML(String fxmlFile, Object fxmlController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxmlFile + ".fxml"));
        fxmlLoader.setController(fxmlController);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
