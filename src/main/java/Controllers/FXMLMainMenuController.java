/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 2256169
 */
public class FXMLMainMenuController {

    private final static Logger logger = LoggerFactory.getLogger(FXMLMainMenuController.class);

    @FXML
    Button gravityButton;

    @FXML
    Button collisionButton;

    @FXML
    Button ricochetButton;

    @FXML
    public void initialize() {
    }

    @FXML
    void gravityOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logger.info("Launching Gravity Simulation");
            //-- 1) Load the scene graph from the specified FXML file and 
            // associate it with its FXML controller.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Gravity.fxml"));
            loader.setController(new GravityController());
            Pane root = loader.load();
            //-- 2) Create and set the scene to the stage.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            // We just need to bring the main window to front.
            stage.setAlwaysOnTop(true);
            stage.show();
            stage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @FXML
    void collisionOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logger.info("Launching Collision Simulation");
            //-- 1) Load the scene graph from the specified FXML file and 
            // associate it with its FXML controller.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Collision.fxml"));
            loader.setController(new CollisionController());
            Pane root = loader.load();
            //-- 2) Create and set the scene to the stage.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            // We just need to bring the main window to front.
            stage.setAlwaysOnTop(true);
            stage.show();
            stage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @FXML
    void ricochetOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logger.info("Launching Ricochet Simulation");
            //-- 1) Load the scene graph from the specified FXML file and 
            // associate it with its FXML controller.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ricochet.fxml"));
            loader.setController(new RicochetController());
            Pane root = loader.load();
            //-- 2) Create and set the scene to the stage.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            // We just need to bring the main window to front.
            stage.setAlwaysOnTop(true);
            stage.show();
            stage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

}
