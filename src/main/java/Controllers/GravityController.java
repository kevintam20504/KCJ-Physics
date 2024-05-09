/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Main.MainApp;
import Models.Ball;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author JD
 */
public class GravityController {
    private Circle Satellite;
    private static Circle  Planet;
    private double velocityX = 0;
    private double velocityY = 1.5;  // Initial tangential velocity scaled for simulation
    
    private static final double Gconstant = 0.1; // Simplified G constant for visible effect
    

    
     private long lastUpdate = 0;

     @FXML
     BorderPane GravityBorderPane;
     
     @FXML
     Pane AnimationPane;
     
     @FXML 
     LineChart SpeedChart;
     
     @FXML 
     LineChart DistanceChart;
     
     @FXML
     Slider PlantMass;
     
     @FXML
     Slider InitialSpeed;
     
     @FXML
     private Slider InitialDistance;
     
     @FXML
     Button StartBtn;
     
     @FXML
     Button ResetBtn;
     
     @FXML
     Button ExitBtn;
  
    private TranslateTransition transition;
   @FXML

   
   
   //buttons
   private void handleExitBtnAction(ActionEvent event) {
    try {
        // Close current window
        Node source = (Node) event.getSource();
        Stage currentStage  = (Stage) source.getScene().getWindow();
        currentStage.close();

        // Open Main window
        MainApp mainApp = new MainApp();
        mainApp.start(new Stage()); // Assumes MainApp extends Application
    } catch (Exception e) {
        
        // Log the error or print stack trace
        e.printStackTrace();
    }
}
    AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;


            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    updateSatellitePosition();
                    lastUpdate = now;
                }
            }
            // Button to start the animation

        };
 @FXML
private void handleStartBtnAction(ActionEvent event) {
    
         if (timer != null) {
                timer.start();
            }
   
}




 
            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    updateSatellitePosition();
                    lastUpdate = now;
                }
            }








      public void initialize() {
        Planet = new Circle(350, 175, 40, Color.BLACK);
        Satellite = new Circle(425, 175, 10, Color.BLUE);
        AnimationPane.getChildren().addAll(Planet, Satellite);
    }
      
      
      /*
      //Collision
      public void checkForCollisionOrBoundary() {
        // Check for collision with the planet
        if (Satellite.getBoundsInParent().intersects(Planet.getBoundsInParent())) {
            showAlert("Collision", "The satellite has collided with the planet!");
        }

        // Check if the satellite leaves the frame
        if ((Satellite.getCenterX()-Planet.getCenterX())>500 ||(Satellite.getCenterY()-Planet.getCenterY())>500 ) {
            showAlert("Boundary Exit", "The satellite has left the viewing area!");
        }
    }
*/
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



    
    



private void updateSatellitePosition() {
        double dx = Planet.getCenterX() - Satellite.getCenterX();
        double dy = Planet.getCenterY() - Satellite.getCenterY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Calculate simplified gravitational force
        double force = Gconstant * (PlantMass.getValue()*100) / (distance * distance);
        double accelerationX = force * dx / distance;
        double accelerationY = force * dy / distance;

        // Update velocity
        velocityX += accelerationX;
        velocityY += accelerationY;

        // Update position
        Satellite.setCenterX(Satellite.getCenterX() + velocityX);
        Satellite.setCenterY(Satellite.getCenterY() + velocityY);
    }

    
    

    
}





    