/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.switchScene("Gravity", new GravityController());
        stage.sizeToScene();
        stage.centerOnScreen();
        logger.info("Loaded Gravity scene");
        
    }

    @FXML
    void collisionOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.switchScene("Collision", new CollisionController());
        stage.sizeToScene();
        stage.centerOnScreen();
        logger.info("Loaded Collision scene");
    }

    @FXML
    void ricochetOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.switchScene("RichochetVF1.18", new RichochetController());
        stage.sizeToScene();
        stage.centerOnScreen();
        logger.info("Loaded Richochet scene");
    }

}
