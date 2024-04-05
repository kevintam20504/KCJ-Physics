/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author JD
 */
public class CollisionController {

    private final static Logger logger = LoggerFactory.getLogger(CollisionController.class);

    @FXML
    BorderPane borderPane;

    @FXML
    Button resetButton;

    @FXML
    Button startStopButton;

    @FXML
    Button exitButton;

    @FXML
    Slider mass1Slider;

    @FXML
    Slider mass2Slider;

    @FXML
    Slider velocity1Slider;

    @FXML
    Slider velocity2Slider;

    @FXML
    Slider elasticitySlider;

    @FXML
    TextField mass1Textfield;

    @FXML
    TextField mass2Textfield;

    @FXML
    TextField velocity1Textfield;

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
    TextField xValue;

    @FXML
    TextField yValue;

    @FXML
    public void initialize() {
        //shows the slider values on the textfields
        mass1Textfield.textProperty().bind(mass1Slider.valueProperty().asString("%.0f" + "g"));
        mass2Textfield.textProperty().bind(mass2Slider.valueProperty().asString("%.0f" + "g"));
        velocity1Textfield.textProperty().bind(velocity1Slider.valueProperty().asString("%.0f" + "m/s"));
        velocity2Textfield.textProperty().bind(velocity2Slider.valueProperty().asString("%.0f" + "m/s"));
        elasticityTextfield.textProperty().bind(elasticitySlider.valueProperty().asString("%.0f"));
        
        borderPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println(newVal);
        });

        System.out.println(block1.getLayoutX());
        System.out.println(block2.getLayoutX());

//        Path path = new Path();
//        path.getElements().add(new MoveTo(0f,0f));
//        path.getElements().add(new LineTo(800f,0f));
//        path.getElements().add(new LineTo(100,0));
//        PathTransition pt = new PathTransition(Duration.seconds(2),path, block1);
//        pt.setCycleCount(1);
//        pt.play();
        //for testing purposes
        MainApp.scene.setOnMouseMoved(e -> {
            xValue.setText("x: " + e.getX());
            yValue.setText("y: " + e.getY());
        });

    }

    @FXML
    void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.switchScene("MainMenu", new FXMLMainMenuController());
        stage.sizeToScene();
        stage.centerOnScreen();
        logger.info("Exited Collision scene");
    }

}
