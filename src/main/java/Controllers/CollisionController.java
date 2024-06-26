/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import Main.CollisionPhysics;
import javafx.animation.AnimationTimer;
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
    CheckBox reflectingBorderCheckBox;

    @FXML
    TextField collisionCountTf;

    @FXML
    TextField timerTf;

    @FXML
    LineChart<Number, Number> block1Graph;

    @FXML
    LineChart<Number, Number> block2Graph;

    @FXML
    NumberAxis block1TimeAxis;

    @FXML
    NumberAxis block2TimeAxis;

    @FXML
    NumberAxis block1VelAxis;

    @FXML
    NumberAxis block2VelAxis;

    XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    XYChart.Series<Number, Number> series2 = new XYChart.Series<>();

    CollisionPhysics physics = new CollisionPhysics();
    AnimationTimer animationTimer;
    int collisionCount = 0;
    boolean isPlaying;

    double lastUpdate = 0;
    double elapsedTime = 0;

    @FXML
    public void initialize() {
        updatePhysics();

        setupGraphs();

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
    }

    //updates values for collisionPhysics from sliders
    public void updatePhysics() {
        physics.setMass1((long) mass1Slider.getValue());
        physics.setVelocity1(velocity1Slider.getValue());
        physics.setMass2((long) mass2Slider.getValue());
        physics.setVelocity2(velocity2Slider.getValue());
        physics.setElasticity(elasticitySlider.getValue() / 100);
    }

    //creates the animation
    public void createAnimation() {
        animationTimer = new AnimationTimer() {
            double velocity1 = physics.getVelocity1();
            double velocity2 = physics.getVelocity2();

            double startTime = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                //updates elapsed time and timer
                elapsedTime = lastUpdate + (System.currentTimeMillis() - startTime) / 1000;
                timerTf.setText(String.valueOf(Math.round(elapsedTime * 100) / 100.0));

                updateGraph(block1Graph, series1, elapsedTime, velocity1);
                updateGraph(block2Graph, series2, elapsedTime, velocity2);

                for (int i = 0; i < 100; i++) {
                    //moves blocks
                    block1.setTranslateX(block1.getTranslateX() + velocity1 / 100);
                    block2.setTranslateX(block2.getTranslateX() + velocity2 / 100);

                    //if blocks collide
                    if (block1.getBoundsInParent().intersects(block2.getBoundsInParent())) {
                        updateGraph(block1Graph, series1, elapsedTime, velocity1);
                        updateGraph(block2Graph, series2, elapsedTime, velocity2);
                        do {
                            block1.setTranslateX(block1.getTranslateX() - velocity1 / 100);
                            block2.setTranslateX(block2.getTranslateX() - velocity2 / 100);
                        } while (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));

                        if ((velocity2 == 0 && block2.getTranslateX() == AnchorPane.getRightAnchor(block2)) || (velocity1 == 0 && block1.getTranslateX() == -AnchorPane.getLeftAnchor(block1))) {
                            velocity1 = 0.0;
                            velocity2 = 0.0;
                            animationTimer.stop();
                        } else {
                            collisionCountTf.setText(String.valueOf(++collisionCount));
                            //sets the velocity to the velocity after collision
                            velocity1 = physics.getVelocity1Final();
                            velocity2 = physics.getVelocity2Final();
                        }
                        //updates the initial velocity
                        physics.setVelocity1(velocity1);
                        physics.setVelocity2(velocity2);
                    }

                    //if block1 collides with wall
                    if (block1.getLayoutX() + block1.getTranslateX() < 0.0) {
                        updateGraph(block1Graph, series1, elapsedTime, velocity1);
                        updateGraph(block2Graph, series2, elapsedTime, velocity2);
                        if (reflectingBorderCheckBox.isSelected()) {
                            block1.setTranslateX(-AnchorPane.getLeftAnchor(block1));
                            collisionCountTf.setText(String.valueOf(++collisionCount));
                            if (physics.getElasticity() < 1 && -velocity1 < 0.1) {
                                velocity1 = 0;
                            } else {
                                //makes velocity in opposite direction and adjusts velocity depending on elasticity
                                velocity1 = -velocity1 * physics.getElasticity();
                            }
                            physics.setVelocity1(velocity1);
                        }
                    }

                    //if block2 collides with wall
                    if (block2.getLayoutX() + block2.getTranslateX() > borderPane.getWidth() - block2.getWidth()) {
                        updateGraph(block1Graph, series1, elapsedTime, velocity1);
                        updateGraph(block2Graph, series2, elapsedTime, velocity2);
                        if (reflectingBorderCheckBox.isSelected()) {
                            block2.setTranslateX(AnchorPane.getRightAnchor(block2));
                            collisionCountTf.setText(String.valueOf(++collisionCount));
                            if (physics.getElasticity() < 1 && velocity2 < 0.1) {
                                velocity2 = 0;
                            } else {
                                //makes velocity in opposite direction and adjusts velocity depending on elasticity
                                velocity2 = -velocity2 * physics.getElasticity();
                            }
                            physics.setVelocity2(velocity2);
                        }
                    }
                }
            }
        };
    }

    void setupGraphs() {
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/chart/NumberAxis.html
        //makes zero not in range
        block1TimeAxis.setForceZeroInRange(false);
        block2TimeAxis.setForceZeroInRange(false);

        //makes ticks not visible
        block1TimeAxis.setTickLabelsVisible(false);
        block2TimeAxis.setTickLabelsVisible(false);
        block1TimeAxis.setOpacity(0);
        block2TimeAxis.setOpacity(0);
    }

    //updates graph with specified values
    void updateGraph(LineChart graph, XYChart.Series<Number, Number> series, double xValue, double yValue) {
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/chart/LineChart.html
        graph.getData().clear();
        series.getData().add(new XYChart.Data<>(xValue, yValue));

        //removes data at certain size to graph isn't squeezed
        if (series.getData().size() > 200) {
            series.getData().remove(0);
        }
        graph.getData().add(series);
    }

    //if animation is running it pauses, otherwise it plays the animation
    @FXML
    void startStopButtonOnAction(ActionEvent event) {
        if (isPlaying) {
            animationTimer.stop();
            isPlaying = false;
            lastUpdate = elapsedTime;

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
        if (animationTimer != null) {
            animationTimer.stop();
        }

        //resetting the collision count
        collisionCount = 0;
        collisionCountTf.setText("0");

        lastUpdate = 0;
        timerTf.setText("0");//resets timer

        //resets position of blocks
        block1.setTranslateX(0);
        block2.setTranslateX(0);

        updatePhysics();//reupdates physics with the values from sliders
        isPlaying = false;

        //clears graph data
        series1.getData().clear();
        series2.getData().clear();
        block1Graph.getData().clear();
        block2Graph.getData().clear();

        //sets bounds for graphs
        block1VelAxis.setLowerBound(-10);
        block1VelAxis.setUpperBound(10);
        block2VelAxis.setLowerBound(-10);
        block2VelAxis.setUpperBound(10);

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

        if (animationTimer != null) {
            animationTimer.stop();
        }

        logger.info("Exited Collision scene");
    }
}
