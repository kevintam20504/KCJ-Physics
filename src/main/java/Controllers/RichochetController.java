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
    Pane renderingPane;
    
     @FXML
    Pane Paneforscene;

   // @FXML
   // Rectangle background;

    @FXML
    SplitMenuButton DDMMaterialofWall;
    
    @FXML
    Button BtnStart;

    // @FXML
   // ToggleButton tgbtnDampingOFF;
    
    @FXML
    Button btnPause;

    @FXML
    Button btnReset;

      @FXML
    Slider  SldWallAngle;
    @FXML
    Slider SldAngleOfWall;

    @FXML
    Slider SlfSpeedOfProjectile;
    @FXML
    Slider sldSlfAngleofShot;
    
    @FXML
    
    Slider sldSWindressistance;

    @FXML
    ToggleButton BtnToggleButton;

    @FXML 
    LineChart GrpDistance;
    
     @FXML 
    LineChart GrpSpeed;
     
     @FXML
private Line wall;
     
   
     
           private Circle ball;
    private double velocityX = 2;
    private double velocityY = 0;
    private double accelerationY = 0.1;
    private AnimationTimer animationTimer;

   @FXML
public void initialize() {
    createBall();
    //createWall();
    //setupButtonHandler();
     Wanderstellen();
    // initEventHandlers();
    startBallMovement();  
           
}
private void createBall() {
    // Create the ball
    ball = new Circle(10);
    ball.setLayoutX(50); 
    ball.setLayoutY(300); 
    Paneforscene.getChildren().add(ball);
}


private Line slantedWall;
 private double wallAngle = 120;  
private void Wanderstellen(){

double centerX = 200+Paneforscene.getWidth() ;
        double centerY = 300;

        slantedWall = new Line(centerX - 100, centerY, centerX + 100, centerY);
        slantedWall.setStroke(Color.BLACK);
        Rotate rotateWall = new Rotate(wallAngle, centerX, centerY);
      //  Rotate rotateBall = new Rotate(0, centerX, centerY);

        slantedWall.getTransforms().add(rotateWall);
 Paneforscene.getChildren().add(slantedWall);
}





private void startBallMovement() {
    // Set initial speed of the ball to 10
    velocityX = 10;
    velocityY = 0;

    animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
        
            ball.setLayoutX(ball.getLayoutX() + velocityX);
            ball.setLayoutY(ball.getLayoutY() + velocityY);

        
            if (ball.getBoundsInParent().intersects(slantedWall.getBoundsInParent())) {
               
                double wallAngle = Math.toRadians(SldWallAngle.getValue());
                double newAngle = wallAngle * 2;
                velocityX = Math.cos(newAngle) * 10;
                velocityY =Math.cos(newAngle) * 10;
                //velocityX = Math.cos(newAngle) * SlfSpeedOfProjectile.getValue();
                //velocityY = Math.sin(newAngle) * SlfSpeedOfProjectile.getValue();
            }
        }
    };
    animationTimer.start();
}




}


/*
private double initialSpeed = 50;
private double wallAngleDegrees = 45;
private void startAnimation() {
    velocityX = initialSpeed; 
    velocityY = 0; 

    animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateBallPosition();
        }
    };
    animationTimer.start();
}

private void updateBallPosition() {
    ball.setLayoutX(ball.getLayoutX() + velocityX);
    if (ball.getLayoutX() >= wall.getStartX() - ball.getRadius()) {
        handleCollision();
    }
}

private void handleCollision() {
    // Stop the ball or reverse direction, handle as per requirement
    velocityX = 0; // Stop the ball for simplicity, or reverse it as needed
}

//public void initEventHandlers(){
//btnStart.setOnAction(event -> startAnimation());

//}

/*
    private void startAnimation() {
       
        renderingPane.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                startAnimationAfterSceneInitialized();
            }
        });
    }

    private void startAnimationAfterSceneInitialized() {
       
        animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
           
            ball.setLayoutX(ball.getLayoutX() + velocityX);
            ball.setLayoutY(ball.getLayoutY() + velocityY);

          
            velocityY += accelerationY;

            
            if (ball.getLayoutX() >= wall.getStartX() - ball.getRadius()) {
                double wallAngleDeg = SldWallAngle.getValue(); 
                double reboundAngleDeg = 2 * wallAngleDeg;
                double reboundAngleRad = Math.toRadians(reboundAngleDeg);

              
                velocityX = -Math.abs(velocityX) * Math.cos(reboundAngleRad); 
                velocityY = -Math.abs(velocityY) * Math.sin(reboundAngleRad); 

            
                ball.setLayoutX(wall.getStartX() - ball.getRadius());
            }

           
            if (ball.getLayoutY() >= Paneforscene.getHeight() - ball.getRadius()) {
                velocityY *= -0.8;
                ball.setLayoutY(Paneforscene.getHeight() - ball.getRadius());
            }
        }
    };

  
    animationTimer.start();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    private void setupButtonHandler() {
    btnStart.setOnAction(event -> startAnimation());
    btnPause.setOnAction(event -> toggleAnimation());
    btnReset.setOnAction(event -> resetAnimation());
}

private void toggleAnimation() {
    if (animationTimer != null) {
        if (BtnToggleButton.isSelected()) {
            animationTimer.stop();
        } else {
            animationTimer.start();
        }
    }
}

private void resetAnimation() {
    if (animationTimer != null) {
        animationTimer.stop();
    }
    ball.setLayoutX(50);
    ball.setLayoutY(50);
    velocityX = 2;
    velocityY = 0;
}
   */

