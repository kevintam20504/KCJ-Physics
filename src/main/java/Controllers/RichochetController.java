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

    private double gravity =.098;
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
 
     SldSpeed.setMin(20);
    SldSpeed.setMax(80);
    SldSpeed.setValue(21); 
    
      SldShotAngle.setMin(0);
    SldShotAngle.setMax(10);
    SldShotAngle.setValue(0);
     SldShotAngle.setBlockIncrement(0.1);

  
    SldSpeed.valueProperty().addListener((obs, oldVal, newVal) -> {
        velocityX = newVal.doubleValue();
        System.out.println("Speed adjusted to: " + velocityX);  
    });      
    
     /* SldShotAngle.valueProperty().addListener((obs, oldVal, newVal) -> {
        adjustShootingAngle(newVal.doubleValue());
    });*/
}
private void createBall() {
 
    ball = new Circle(10);
    ball.setLayoutX(50); 
    ball.setLayoutY(200); 
    Paneforscene.getChildren().add(ball);
}
private void adjustShootingAngle(double angle) {
    double angleInRadians = Math.toRadians(angle); 
    double baseSpeed = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
      velocityX = Math.cos(angleInRadians) * baseSpeed;
      System.out.println("Adjusted velocities -> VelocityX: " + velocityX + ", VelocityY: " + velocityY);
   resetSimulation();
}

 private Line slantedWall;
private final double wallAngle =0; 

private void Wanderstellen(){
    double centerX = 200 + Paneforscene.getWidth();
    double centerY = 210;

    slantedWall = new Line(centerX - 50, centerY, centerX + 100, centerY);
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

// velocity y = velocityx*cos(2*-angle);
private boolean gravityEnabled = false;

private void startSimulation() {
    if (animationTimer == null) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                if (gravityEnabled) {
                    velocityY += gravity;
                }

                double newX = ball.getLayoutX() + velocityX;
                double newY = ball.getLayoutY() + velocityY;

                ball.setLayoutX(newX);
                ball.setLayoutY(newY);

               
                if (ball.getBoundsInParent().intersects(slantedWall.getBoundsInParent())) {
                    
                    gravityEnabled = true;

                   double wallAngle = -SldWallAngle.getValue();
   

    double newAngle = Math.toRadians(2 * wallAngle);
     double initialUpwardVelocity = 10;  //velocityX*Math.cos(wallAngle) ;  
    velocityX = Math.cos(newAngle) * 10;
    velocityY = -Math.abs(Math.sin(newAngle) * initialUpwardVelocity); 

   
    System.out.println("test for new velocities after wall collision -> VelocityX: " + velocityX + ", VelocityY: " + velocityY);
}
            }
        };
    }
    animationTimer.start();
    BtnStart.setDisable(true);
    BtnStop.setDisable(false);
    BtnReset.setDisable(false);
}
  
  private void updateVerticalVelocity(double angle) {

    double angleInRadians = Math.toRadians(-2 * angle);
    velocityY = velocityX * Math.cos(angleInRadians);
    System.out.println("Updated VelocityY: " + velocityY);
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
        ball.setLayoutY(150);
        velocityX = 2;
        velocityY = 0;
        
        slantedWall.getTransforms().clear();
    rotateWall(SldWallAngle.getValue());
        BtnReset.setDisable(true);
        BtnStart.setDisable(false);
         startSimulation();
    }
  
  private void rotateWall(double angle) {
        Rotate rotate = new Rotate(-angle, slantedWall.getStartX(), slantedWall.getStartY());
        slantedWall.getTransforms().clear();
        slantedWall.getTransforms().add(rotate);
    }
}

