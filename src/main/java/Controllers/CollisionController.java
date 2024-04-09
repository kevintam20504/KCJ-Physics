/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import Main.CollisionPhysics;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.HLineTo;
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

    PathTransition block1PathTransition = new PathTransition();
    PathTransition block2PathTransition = new PathTransition();

    @FXML
    public void initialize() {

        CollisionPhysics physics = new CollisionPhysics();

        //detects if if width of window changes to change the appropriate distance between blocks
        borderPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            CollisionPhysics.setDistance(block2.getLayoutX() - (block1.getLayoutX() + 100));
            System.out.println("distance: " + CollisionPhysics.getDistance());
            System.out.println("block1X: " + block1.getLayoutX());
            System.out.println("block2X: " + block2.getLayoutX());
        });

        //shows the slider values on the textfields
        mass1Textfield.textProperty().bind(mass1Slider.valueProperty().asString("%.0f" + "g"));
        mass2Textfield.textProperty().bind(mass2Slider.valueProperty().asString("%.0f" + "g"));
        velocity1Textfield.textProperty().bind(velocity1Slider.valueProperty().asString("%.1f" + "m/s"));
        velocity2Textfield.textProperty().bind(velocity2Slider.valueProperty().asString("%.1f" + "m/s"));
        elasticityTextfield.textProperty().bind(elasticitySlider.valueProperty().asString("%.0f" + "%%"));

        //checks the slider value after changing and updates the physics for these values
        mass1Slider.setOnMouseReleased(e -> {
            physics.setMass1(Math.round(mass1Slider.getValue()));
        });
        mass1Slider.setOnKeyReleased(e -> {
            physics.setMass1(Math.round(mass1Slider.getValue()));
        });

        velocity1Slider.setOnMouseReleased(e -> {
            physics.setVelocity1(Math.round(velocity1Slider.getValue() * 10) / 10.0);
        });
        velocity1Slider.setOnKeyReleased(e -> {
            physics.setVelocity1(Math.round(velocity1Slider.getValue() * 10) / 10.0);
        });

        mass2Slider.setOnMouseReleased(e -> {
            physics.setMass2(Math.round(mass2Slider.getValue()));
        });
        mass2Slider.setOnKeyReleased(e -> {
            physics.setMass2(Math.round(mass2Slider.getValue()));
        });

        velocity2Slider.setOnMouseReleased(e -> {
            physics.setVelocity2(Math.round(velocity2Slider.getValue() * 10) / 10.0);
        });
        velocity2Slider.setOnKeyReleased(e -> {
            physics.setVelocity2(Math.round(velocity2Slider.getValue() * 10) / 10.0);
        });

        elasticitySlider.setOnMouseReleased(e -> {
            physics.setElasticity(Math.round(elasticitySlider.getValue()));
        });
        elasticitySlider.setOnKeyReleased(e -> {
            physics.setElasticity(Math.round(elasticitySlider.getValue()));
        });

        //for testing purposes
        MainApp.scene.setOnMouseMoved(e -> {
            xValue.setText("x: " + e.getX());
            yValue.setText("y: " + e.getY());
        });

    }

    //creates the animation
    public void drawAnimation() {
        //note: to look into: cue points
        Path path1 = new Path();
        path1.getElements().add(new MoveTo(0, 0));
        path1.getElements().add(new HLineTo(400));
        path1.getElements().add(new HLineTo(-500));
        block1PathTransition.setDuration(Duration.seconds(13));
        block1PathTransition.setPath(path1);
        block1PathTransition.setNode(block1);
        block1PathTransition.setCycleCount(1);

        Path path2 = new Path();
        path2.getElements().add(new MoveTo(0, 0));
        path2.getElements().add(new HLineTo(-400));
        path2.getElements().add(new HLineTo(500));
        block2PathTransition.setDuration(Duration.seconds(13));
        block2PathTransition.setPath(path2);
        block2PathTransition.setNode(block2);
        block2PathTransition.setCycleCount(1);
    }

    //if animation is running it pauses, otherwise it plays the animation
    @FXML
    void startStopButtonOnAction(ActionEvent event) {
        if (block1PathTransition.getStatus() == PathTransition.Status.RUNNING) {
            block1PathTransition.pause();
            block2PathTransition.pause();
            logger.info("Pausing Collision Animation");
        } else {
            drawAnimation();
            block1PathTransition.play();
            block2PathTransition.play();
            logger.info("Playing Collision Animation");
        }
    }

    //resets the animation
    @FXML
    void resetButtonOnAction(ActionEvent event) {
        block1PathTransition.jumpTo(Duration.ZERO);
        block2PathTransition.jumpTo(Duration.ZERO);
        block1PathTransition.stop();
        block2PathTransition.stop();
        logger.info("Resetting Collision Animation");
    }

    //exits the simulation
    @FXML
    void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.switchScene("MainMenu", new FXMLMainMenuController());
        stage.sizeToScene();
        stage.centerOnScreen();
        logger.info("Exited Collision scene");
    }

}
