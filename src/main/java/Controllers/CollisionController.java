/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import Main.CollisionPhysics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    BorderPane borderPane;

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
    TextField xValue;

    @FXML
    TextField yValue;

    @FXML
    CheckBox reflectingBorderCheckBox;

    @FXML
    TextField collisionCountTf;

    final NumberAxis velocity1Axis = new NumberAxis();
    final NumberAxis velocity2Axis = new NumberAxis();
    final NumberAxis xAxis = new NumberAxis();

    @FXML
    LineChart block1Graph = new LineChart(xAxis, velocity1Axis);

    @FXML
    LineChart block2Graph = new LineChart(xAxis, velocity2Axis);

    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();

    CollisionPhysics physics = new CollisionPhysics();
    AnimationTimer animationTimer;
    int collisionCount = 0;
    boolean isPlaying;

    @FXML
    public void initialize() {

        block1Graph.setCreateSymbols(false);
        block2Graph.setCreateSymbols(false);

        block1Graph.getYAxis().setLabel("block1 vel");
        //block1Graph.getYAxis().setAnimated(true);
        block2Graph.getYAxis().setLabel("block2 vel");
        //block2Graph.getYAxis().setAnimated(true);
        block1Graph.getXAxis().setLabel("time");
        //block1Graph.getXAxis().setAnimated(true);
        block2Graph.getXAxis().setLabel("time");
        //block2Graph.getXAxis().setAnimated(true);

        series1.setName("Block1");
        series2.setName("Block2");

        block1Graph.setAnimated(false);
        block2Graph.setAnimated(false);

        updatePhysics();

        //shows the slider values on the textfields
        mass1Textfield.textProperty().bind(mass1Slider.valueProperty().asString("%.0f" + "kg"));
        mass2Textfield.textProperty().bind(mass2Slider.valueProperty().asString("%.0f" + "kg"));
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

    //setting the default values
    public void updatePhysics() {
        physics.setMass1((long) mass1Slider.getValue());
        physics.setVelocity1(velocity1Slider.getValue());
        physics.setMass2((long) mass2Slider.getValue());
        physics.setVelocity2(velocity2Slider.getValue());
        physics.setElasticity(elasticitySlider.getValue() / 100);
        CollisionPhysics.setDistance((block2.getLayoutX() - (block1.getLayoutX() + 100.0)) / 100.0);
    }

    public void createAnimation() {
        animationTimer = new AnimationTimer() {
            double change1 = physics.getVelocity1();
            double change2 = physics.getVelocity2();

            double startTime = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                double elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

                block1Graph.getData().clear();
                block2Graph.getData().clear();

                series1.getData().add(new XYChart.Data<>(String.valueOf(elapsedTime), change1));
                series2.getData().add(new XYChart.Data<>(String.valueOf(elapsedTime), change2));

                if (series1.getData().size() > 30) {
                    series1.getData().remove(0);
                }

                if (series2.getData().size() > 30) {
                    series2.getData().remove(0);
                }

                block1Graph.getData().add(series1);
                block2Graph.getData().add(series2);

                for (int i = 0; i < 100; i++) {
                    block1.setTranslateX(block1.getTranslateX() + change1 / 100);
                    block2.setTranslateX(block2.getTranslateX() + change2 / 100);

                    if (block1.getBoundsInParent().intersects(block2.getBoundsInParent())) {
                        do {
                            block1.setTranslateX(block1.getTranslateX() - change1 / 100);
                            block2.setTranslateX(block2.getTranslateX() - change2 / 100);
                        } while (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));

                        if ((change2 == 0 && block2.getTranslateX() == AnchorPane.getRightAnchor(block2)) || (change1 == 0 && block1.getTranslateX() == -AnchorPane.getLeftAnchor(block1))) {
                            change1 = 0.0;
                            change2 = 0.0;
                            animationTimer.stop();
                        } else {
                            collisionCountTf.setText(String.valueOf(++collisionCount));
                            change1 = physics.getVelocity1Final();
                            change2 = physics.getVelocity2Final();
                        }
                        physics.setVelocity1(change1);
                        physics.setVelocity2(change2);
                    }

                    if (block1.getLayoutX() + block1.getTranslateX() < 0.0) {
                        if (reflectingBorderCheckBox.isSelected()) {
                            block1.setTranslateX(-AnchorPane.getLeftAnchor(block1));
                            collisionCountTf.setText(String.valueOf(++collisionCount));
                            if (physics.getElasticity() < 1 && -change1 < 0.1) {
                                change1 = 0;
                            } else {
                                change1 = -change1 * physics.getElasticity();
                            }
                            physics.setVelocity1(change1);
                        }
                    }

                    if (block2.getLayoutX() + block2.getTranslateX() > borderPane.getWidth() - block2.getWidth()) {
                        if (reflectingBorderCheckBox.isSelected()) {
                            block2.setTranslateX(AnchorPane.getRightAnchor(block2));
                            collisionCountTf.setText(String.valueOf(++collisionCount));
                            if (physics.getElasticity() < 1 && change2 < 0.1) {
                                change2 = 0;
                            } else {
                                change2 = -change2 * physics.getElasticity();
                            }
                            physics.setVelocity2(change2);
                        }
                    }
                }
            }
        };
    }

    //if animation is running it pauses, otherwise it plays the animation
    @FXML
    void startStopButtonOnAction(ActionEvent event) {
        if (isPlaying) {
            animationTimer.stop();
            isPlaying = false;

            //enables sliders to be adjusted
            mass1Slider.setDisable(false);
            velocity1Slider.setDisable(false);
            mass2Slider.setDisable(false);
            velocity2Slider.setDisable(false);
            elasticitySlider.setDisable(false);

            logger.info("Pausing Collision Animation");
        } else if (!isPlaying) {
            createAnimation();
            animationTimer.start();
            isPlaying = true;

            //disables sliders when animation starts
            mass1Slider.setDisable(true);
            velocity1Slider.setDisable(true);
            mass2Slider.setDisable(true);
            velocity2Slider.setDisable(true);
            elasticitySlider.setDisable(true);

            //disables resizing window
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);

            logger.info("Playing Collision Animation");
        }
    }

    //resets the animation
    @FXML
    void resetButtonOnAction(ActionEvent event) {
        animationTimer.stop();
        collisionCount = 0;
        collisionCountTf.setText("0");

        block1.setTranslateX(0);
        block2.setTranslateX(0);
        updatePhysics();
        isPlaying = false;

        //enables sliders to be adjusted
        mass1Slider.setDisable(false);
        velocity1Slider.setDisable(false);
        mass2Slider.setDisable(false);
        velocity2Slider.setDisable(false);
        elasticitySlider.setDisable(false);

        //enables resizing window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(true);

        logger.info("Resetting Collision Animation");
    }

    //exits the simulation
    @FXML
    void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.switchScene("MainMenu", new FXMLMainMenuController());
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(true);

        animationTimer.stop();
        logger.info("Exited Collision scene");
    }
}
