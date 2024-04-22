/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import Models.Ball;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author JD
 */
public class RichochetController {
     private Circle projectile;
    
   
    
     @FXML
    Pane Paneforscene;



    @FXML
    SplitMenuButton BtnWallMeterial;
    
    @FXML
    Button BtnStart;

    // @FXML
   // ToggleButton tgbtnDampingOFF;
    
    @FXML
    Button BtnStop;

    @FXML
    Button BtnReset;

      @FXML
    Slider  SldWallAngle ;
   

    @FXML
    Slider SldSpeed;
    @FXML
    Slider SldShotAngle;
    
    @FXML
    
    Slider SldSWind;

    @FXML
    ToggleButton BtnGravity;

    @FXML 
    LineChart GrpDistance;
    
     @FXML 
    LineChart GrpSpeed;
     
     
     
   
     
           private Circle ball;
    private double velocityX = 2;
    private double velocityY = 0;
    private double accelerationY = 0.1;
    private AnimationTimer animationTimer;

   @FXML
public void initialize() {
    createBall();
Eventhandelers();
     Wanderstellen();
   initializeHandlers();
  //  startBallMovement();  
           
}
private void createBall() {
 
    ball = new Circle(10);
    ball.setLayoutX(50); 
    ball.setLayoutY(200); 
    Paneforscene.getChildren().add(ball);
}



 private Line slantedWall;
private final double wallAngle = 200; 

private void Wanderstellen(){
    double centerX = 200 + Paneforscene.getWidth();
    double centerY = 200;

    slantedWall = new Line(centerX - 100, centerY, centerX + 100, centerY);
    slantedWall.setStroke(Color.BLACK);
    Rotate rotateWall = new Rotate(wallAngle, centerX, centerY);
    slantedWall.getTransforms().add(rotateWall);

    Paneforscene.getChildren().add(slantedWall);
}

private void startBallMovement() {
    velocityX = 10;
    velocityY = 0;

    animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            ball.setLayoutX(ball.getLayoutX() + velocityX);
            ball.setLayoutY(ball.getLayoutY() + velocityY);

            if (ball.getBoundsInParent().intersects(slantedWall.getBoundsInParent())) {
                double wallAngleRadians = Math.toRadians(wallAngle); 
                double newAngle = wallAngleRadians * 2;
                velocityX = Math.cos(newAngle) * 10;
                velocityY = Math.sin(newAngle) * 10; 
            }
        }
    };
    animationTimer.start();
}

 private void initializeHandlers() {
        BtnStart.setOnAction(e -> startSimulation());
        BtnStop.setOnAction(e -> stopSimulation());
        BtnReset.setOnAction(e -> resetSimulation());

        BtnStop.setDisable(true);
        BtnReset.setDisable(true);

        SldWallAngle.valueProperty().addListener((obs, oldVal, newVal) -> rotateWall(newVal.doubleValue()));
    }
public void Eventhandelers(){
BtnStop.setDisable(true);
        BtnReset.setDisable(true);
    
}

  private void startSimulation() {
        if (animationTimer == null) {
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    double newX = ball.getLayoutX() + velocityX;
                    double newY = ball.getLayoutY() + velocityY;

                    ball.setLayoutX(newX);
                    ball.setLayoutY(newY);

                  
                    if (ball.getBoundsInParent().intersects(slantedWall.getBoundsInParent())) {
                        double wallAngleRadians = Math.toRadians(SldWallAngle.getValue());
                        double newAngle = wallAngleRadians * 2;
                        velocityX = Math.cos(newAngle) * 10;
                        velocityY = Math.sin(newAngle) * 10;
                    }
                }
            };
        }
        animationTimer.start();
        BtnStart.setDisable(true);
        BtnStop.setDisable(false);
        BtnReset.setDisable(false);
    }

 private void stopSimulation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        BtnStart.setDisable(false);
        BtnStop.setDisable(true);
    }
 
  private void resetSimulation() {
        stopSimulation();
        ball.setLayoutX(50);
        ball.setLayoutY(300);
        velocityX = 2;
        velocityY = 0;
        BtnReset.setDisable(true);
        BtnStart.setDisable(false);
    }
  
  private void rotateWall(double angle) {
        Rotate rotate = new Rotate(angle, slantedWall.getStartX(), slantedWall.getStartY());
        slantedWall.getTransforms().clear();
        slantedWall.getTransforms().add(rotate);
    }
}

