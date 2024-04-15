/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import Main.CollisionPhysics;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

    SequentialTransition block1Transition = new SequentialTransition();
    SequentialTransition block2Transition = new SequentialTransition();
    CollisionPhysics physics = new CollisionPhysics();

    @FXML
    public void initialize() {
        //setting the default values
        physics.setMass1(mass1Slider.getValue() / 1000);
        physics.setVelocity1(velocity1Slider.getValue());
        physics.setMass2(mass2Slider.getValue() / 1000);
        physics.setVelocity2(-velocity2Slider.getValue());
        physics.setElasticity(elasticitySlider.getValue() / 100);

        //checks if window is resised
        //detects if if the X layout of block2 changes to change the appropriate distance between blocks
        block2.layoutXProperty().addListener((observable, oldValue, newValue) -> {
            CollisionPhysics.setDistance((block2.getLayoutX() - (block1.getLayoutX() + 100)) / 100);
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
            physics.setMass1(Math.round(mass1Slider.getValue()) / 1000.0);
        });
        mass1Slider.setOnKeyReleased(e -> {
            physics.setMass1(Math.round(mass1Slider.getValue()) / 1000.0);
        });

        velocity1Slider.setOnMouseReleased(e -> {
            physics.setVelocity1(Math.round(velocity1Slider.getValue() * 10) / 10.0);
        });
        velocity1Slider.setOnKeyReleased(e -> {
            physics.setVelocity1(Math.round(velocity1Slider.getValue() * 10) / 10.0);
        });

        mass2Slider.setOnMouseReleased(e -> {
            physics.setMass2(Math.round(mass2Slider.getValue()) / 1000.0);
        });
        mass2Slider.setOnKeyReleased(e -> {
            physics.setMass2(Math.round(mass2Slider.getValue()) / 1000.0);
        });

        velocity2Slider.setOnMouseReleased(e -> {
            physics.setVelocity2(-Math.round(velocity2Slider.getValue() * 10) / 10.0);
        });
        velocity2Slider.setOnKeyReleased(e -> {
            physics.setVelocity2(-Math.round(velocity2Slider.getValue() * 10) / 10.0);
        });

        elasticitySlider.setOnMouseReleased(e -> {
            physics.setElasticity(Math.round(elasticitySlider.getValue()) / 100.0);
        });
        elasticitySlider.setOnKeyReleased(e -> {
            physics.setElasticity(Math.round(elasticitySlider.getValue()) / 100.0);
        });

        //for testing purposes
        MainApp.scene.setOnMouseMoved(e -> {
            xValue.setText("x: " + e.getX());
            yValue.setText("y: " + e.getY());
        });

    }

    //creates the animation
//    public void drawAnimation() {
//        System.out.println(physics);
//        //note: to look into: cue points
//        Path path1 = new Path();
//        path1.getElements().add(new MoveTo(0, 0));
//        path1.getElements().add(new HLineTo(physics.getBlock1DistanceTravelled() * 100));
//        //path1.getElements().add(new HLineTo(-500));
//        //block1PathTransition.setDuration(Duration.seconds(physics.getCollisionTime()));
//        block1PathTransition.setDuration(Duration.seconds(2));
//        block1PathTransition.setPath(path1);
//        block1PathTransition.setNode(block1);
//        block1PathTransition.setCycleCount(1);
//        
//
//        Path path2 = new Path();
//        path2.getElements().add(new MoveTo(0, 0));
//        path2.getElements().add(new HLineTo(-physics.getBlock2DistanceTravelled() * 100));
//        //path2.getElements().add(new HLineTo(500));
//        //block2PathTransition.setDuration(Duration.seconds(physics.getCollisionTime()));
//        block2PathTransition.setDuration(Duration.seconds(6));
//        block2PathTransition.setPath(path2);
//        block2PathTransition.setNode(block2);
//        block2PathTransition.setCycleCount(1);
//        block2PathTransition.setRate(3);
//    }
    //creates the animation
    //https://docs.oracle.com/javase/8/javafx/api/javafx/animation/SequentialTransition.html
    //https://openjfx.io/javadoc/14/javafx.graphics/javafx/animation/Transition.html#interpolatorProperty
    public void drawAnimation() {
        System.out.println(physics);

        TranslateTransition b1BeforeCollision = new TranslateTransition(Duration.seconds(physics.getCollisionTime()), block1);
        b1BeforeCollision.setFromX(0);//from start of block1
        b1BeforeCollision.setToX(physics.getBlock1DistanceTravelled() * 100);//to point of collision

        TranslateTransition b2BeforeCollsion = new TranslateTransition(Duration.seconds(physics.getCollisionTime()), block2);
        b2BeforeCollsion.setFromX(0);//from start of block2
        b2BeforeCollsion.setToX(-physics.getBlock2DistanceTravelled() * 100);//to point of collision

        TranslateTransition b1AfterCollision = new TranslateTransition();
        b1AfterCollision.setNode(block1);
        b1AfterCollision.setFromX(physics.getBlock1DistanceTravelled() * 100);//from point of collision

        TranslateTransition b2AfterCollision = new TranslateTransition();
        b2AfterCollision.setNode(block2);
        b2AfterCollision.setFromX(-physics.getBlock2DistanceTravelled() * 100);//from point of collision

        double b1DistFromEdge = AnchorPane.getLeftAnchor(block1) / 100;
        double b2DistFromEdge = AnchorPane.getRightAnchor(block2) / 100;

        if (physics.getVelocity1Final() < 0) {
            double dist = physics.getBlock1DistanceTravelled() + b1DistFromEdge;
            double time = dist / Math.abs(physics.getVelocity1Final());

            b1AfterCollision.setToX(-b1DistFromEdge * 100);
            b1AfterCollision.setDuration(Duration.seconds(time));

        } else if (physics.getVelocity1Final() > 0) {
            double dist = physics.getBlock2DistanceTravelled() + b2DistFromEdge;
            double time = dist / Math.abs(physics.getVelocity1Final());

            b1AfterCollision.setToX((physics.getBlock1DistanceTravelled() + dist) * 100);
            b1AfterCollision.setDuration(Duration.seconds(time));
        }

        if (physics.getVelocity2Final() > 0) {
            double dist = physics.getBlock2DistanceTravelled() + b2DistFromEdge;
            double time = dist / Math.abs(physics.getVelocity2Final());

            b2AfterCollision.setToX(b2DistFromEdge * 100);
            b2AfterCollision.setDuration(Duration.seconds(time));

        } else if (physics.getVelocity2Final() < 0) {
            double dist = physics.getBlock1DistanceTravelled() + b1DistFromEdge;
            double time = dist / Math.abs(physics.getVelocity2Final());

            b2AfterCollision.setToX(-(physics.getBlock2DistanceTravelled() + dist) * 100);
            b2AfterCollision.setDuration(Duration.seconds(time));
        }

        b1BeforeCollision.setInterpolator(Interpolator.LINEAR);
        b2BeforeCollsion.setInterpolator(Interpolator.LINEAR);
        b1AfterCollision.setInterpolator(Interpolator.LINEAR);
        b2AfterCollision.setInterpolator(Interpolator.LINEAR);

        block1Transition.getChildren().clear();
        block2Transition.getChildren().clear();
        block1Transition.getChildren().addAll(b1BeforeCollision, b1AfterCollision);
        block2Transition.getChildren().addAll(b2BeforeCollsion, b2AfterCollision);
    }

//    public void drawAnimation() {
//        System.out.println(physics);
//        block1Transition.setCycleCount(1);
//        block2Transition.setCycleCount(1);
//
//        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(physics.getCollisionTime()), block1);
//        TranslateTransition tt2 = new TranslateTransition(Duration.seconds((physics.getBlock1DistanceTravelled() + 3) / Math.abs(physics.getVelocity1Final())), block1);
//        tt1.setInterpolator(Interpolator.LINEAR);
//        tt2.setInterpolator(Interpolator.LINEAR);
//
//        tt1.setFromX(0);//from start of block1
//        tt1.setToX(physics.getBlock1DistanceTravelled() * 100);//to point of collision
//        tt2.setFromX(physics.getBlock1DistanceTravelled() * 100);//from point of collision
//        tt2.setToX(-300);
//
//        TranslateTransition tt3 = new TranslateTransition(Duration.seconds(physics.getCollisionTime()), block2);
//        TranslateTransition tt4 = new TranslateTransition(Duration.seconds((physics.getBlock2DistanceTravelled() + 3) / Math.abs(physics.getVelocity2Final())), block2);
//        tt3.setInterpolator(Interpolator.LINEAR);
//        tt4.setInterpolator(Interpolator.LINEAR);
//
//        tt3.setFromX(0);
//        tt3.setToX(-physics.getBlock2DistanceTravelled() * 100);
//        tt4.setFromX(-physics.getBlock2DistanceTravelled() * 100);
//        tt4.setToX(300);
//
//        block1Transition.getChildren().clear();
//        block2Transition.getChildren().clear();
//        block1Transition.getChildren().addAll(tt1, tt2);
//        block2Transition.getChildren().addAll(tt3, tt4);
//    }
    //if animation is running it pauses, otherwise it plays the animation
    @FXML
    void startStopButtonOnAction(ActionEvent event) {
        drawAnimation();
        if (block1Transition.getStatus().equals(Transition.Status.RUNNING) || block2Transition.getStatus().equals(Transition.Status.RUNNING)) {
            block1Transition.pause();
            block2Transition.pause();
            logger.info("Pausing Collision Animation");
        } else {
            if (block1Transition.getStatus().equals(Transition.Status.PAUSED) || block1Transition.getCurrentTime().equals(Duration.ZERO)) {
                block1Transition.play();
                logger.info("Playing block1 Collision Animation");
            }
            if (block2Transition.getStatus().equals(Transition.Status.PAUSED) || block2Transition.getCurrentTime().equals(Duration.ZERO)) {
                block2Transition.play();
                logger.info("Playing block2 Collision Animation");
            }
        }
    }

    //resets the animation
    @FXML
    void resetButtonOnAction(ActionEvent event) {
        block1Transition.playFromStart();
        block2Transition.playFromStart();
        block1Transition.stop();
        block2Transition.stop();
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
