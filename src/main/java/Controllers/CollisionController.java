/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import Main.CollisionPhysics;
import javafx.animation.Animation;
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

    PathTransition pt = new PathTransition();

    @FXML
    public void initialize() {
        //shows the slider values on the textfields
        mass1Textfield.textProperty().bind(mass1Slider.valueProperty().asString("%.0f" + "g"));
        mass2Textfield.textProperty().bind(mass2Slider.valueProperty().asString("%.0f" + "g"));
        velocity1Textfield.textProperty().bind(velocity1Slider.valueProperty().asString("%.0f" + "m/s"));
        velocity2Textfield.textProperty().bind(velocity2Slider.valueProperty().asString("%.0f" + "m/s"));
        elasticityTextfield.textProperty().bind(elasticitySlider.valueProperty().asString("%.0f"));

        CollisionPhysics physics = new CollisionPhysics();
        
        //for testing
        CollisionPhysics p = new CollisionPhysics(10, 3, 20, -5, 1);
        CollisionPhysics.setDistance(8);
        System.out.println("vel1: " + p.getVelocity1());
        System.out.println("vel2: " + p.getVelocity2());

        //detects if if width of window changes to change the appropriate distance between blocks
        borderPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            CollisionPhysics.setDistance(block2.getLayoutX() - (block1.getLayoutX() + 100));
            System.out.println("distance: " + CollisionPhysics.getDistance());
            System.out.println("block1X: " + block1.getLayoutX());
            System.out.println("block2X: " + block2.getLayoutX());
        });

        drawAnimation();

        //for testing purposes
        MainApp.scene.setOnMouseMoved(e -> {
            xValue.setText("x: " + e.getX());
            yValue.setText("y: " + e.getY());
        });

    }

    public void drawAnimation() {
        //note: to look into: cue points
        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));
        path.getElements().add(new LineTo(400, 0));
       // path.getElements().add(new LineTo(0, 0));
        pt.setDuration(Duration.seconds(2));
        pt.setPath(path);
        pt.setNode(block1);
        pt.setCycleCount(1);
    }

    @FXML
    void startStopButtonOnAction(ActionEvent event) {
        if (pt.getStatus() == PathTransition.Status.RUNNING) {
            pt.pause();
            logger.info("Pausing Collision Animation");
        } else {
            pt.play();
            logger.info("Playing Collision Animation");
        }
    }

    @FXML
    void resetButtonOnAction(ActionEvent event) {
        pt.jumpTo(Duration.ZERO);
        pt.stop();
        logger.info("Resetting Collision Animation");
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
