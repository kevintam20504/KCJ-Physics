/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author JD
 */
public class CollisionController {

    private final static Logger logger = LoggerFactory.getLogger(CollisionController.class);

    @FXML
    Button resetButton;

    @FXML
    Button startStopButton;

    @FXML
    Button exitButton;

    @FXML
    Slider mass1Slider;

    @FXML
    Slider velocity1Slider;

    @FXML
    Slider mass2Slider;

    @FXML
    Slider velocity2Slider;

    @FXML
    Slider elasticitySlider;

    @FXML
    TextField mass1Textfield;

    @FXML
    TextField velocity1Textfield;

    @FXML
    TextField mass2Textfield;

    @FXML
    TextField velocity2Textfield;

    @FXML
    TextField elasticityTextfield;

    @FXML
    Rectangle block1;

    @FXML
    Rectangle block2;

    @FXML
    LineChart block1Graph;

    @FXML
    LineChart block2Graph;

    @FXML
    public void initialize() {
        System.out.println("wow");
    }

    @FXML
    void exitButtonOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logger.info("Launching Gravity Simulation");
            //-- 1) Load the scene graph from the specified FXML file and 
            // associate it with its FXML controller.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            loader.setController(new FXMLMainMenuController());
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
