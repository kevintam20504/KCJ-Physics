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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author JD
 */
public class RichochetController {
     private Circle projectile;
    
    @FXML
    Pane renderingPane;

   // @FXML
   // Rectangle background;

    @FXML
    SplitMenuButton DDMMaterialofWall;
    
    @FXML
    Button btnStart;

    // @FXML
   // ToggleButton tgbtnDampingOFF;
    
    @FXML
    Button btnPause;

    @FXML
    Button btnReset;

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
     
     
   
     
           private Circle ball;
    private double velocityX = 2;
    private double velocityY = 0;
    private double accelerationY = 0.1;
    private AnimationTimer animationTimer;

   @FXML
public void initialize() {
    createBall();
    //setupButtonHandler();
     initEventHandlers();
      
            System.out.println(" initialized");
}
private void createBall() {
    // Create the ball
    ball = new Circle(10);
    ball.setLayoutX(50); 
    ball.setLayoutY(50); 


    renderingPane.getChildren().add(ball);
}

public void initEventHandlers() {
   
}


private void setupButtonHandler() {
    // Event handler for the Start button
    btnStart.setOnAction(event -> startAnimation());
}

    private void startAnimation() {
        // Wait for the scene to be fully initialized
        renderingPane.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                startAnimationAfterSceneInitialized();
            }
        });
    }

    private void startAnimationAfterSceneInitialized() {
        // Initialize the AnimationTimer
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update ball position
                ball.setLayoutX(ball.getLayoutX() + velocityX);
                ball.setLayoutY(ball.getLayoutY() + velocityY);

                // Apply gravity
                velocityY += accelerationY;

                // If the ball hits the bottom of the pane, reverse the velocity
                if (ball.getLayoutY() >= renderingPane.getHeight()) {
                    velocityY *= -0.8; // Reverse velocity with some loss
                    ball.setLayoutY(renderingPane.getHeight()); // Set position to avoid getting stuck
                }
            }
        };

        // Start the animation
        animationTimer.start();
    }
 
   
}
